package com.techstack.spring.SpringJdbcDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


/**
 * This example has been follow along based on this YouTube vide link
 * https://www.youtube.com/watch?v=TUOwlaqZ0eo&list=PLgGXSWYM2FpPw8rV0tZoMiJYSCiLhPnOc&index=43
 * 
 * Examples:
 * 1. Refer Step-1 - datasource()
 * 2. using spring boot application.propeties configuration, simply create tables and insert data using 
 * schema.sql and data.sql files.
 * 3. Refer: QueryCustomersAndOrdersCount
 * 4. Refer: QueryCustomersAndOrders (one-to-many)
 * 5. Refer: QueryCustomersAndOrdersSimpleFlatMapper (same 4th point scenario but using simple flat mapper)
 * 6. Refer: JdbcTemplateWriter (how to insert a record? and how to retrieve the auto generated key?)
 * 7. 
 * 
 * @author KARTHIKEYAN N
 *
 */
@SpringBootApplication
public class SpringJdbcDemoApplication {
	
	/**
	 * Step - 1
	 * 
	 * This is very traditional way to get the datasource for the given
	 * connection information
	 * 
	 * @return
	 * @throws Exception
	 */
	//@Bean
	DataSource datasource() throws Exception {
		Driver driver = new Driver();
		Connection c = driver.connect(null, null);
		SimpleDriverDataSource sds = new SimpleDriverDataSource(driver, "url", "user", "password");
		return sds;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}
}

/**
 * Scenario - 3 implementation
 * 
 * In this example, we would retrieve the data using JdbcTemplate and map it into DTO object.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Order(1)
@Log4j2
@Component
class QueryCustomersAndOrdersCount implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	public QueryCustomersAndOrdersCount(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		String sql = "select c.*, (select count(o.id) from orders o where o.customer_fk = c.id) as count from customers c ";
		
		Collection<CustomerOrderReport> reports = 
			this.jdbcTemplate.query(sql, new RowMapper<CustomerOrderReport>() {

				@Override
				public CustomerOrderReport mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new CustomerOrderReport(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getLong("count"));	
				}
				
			});
		reports.forEach(log::info);
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CustomerOrderReport {
		private Long customerId;
		private String name, email;
		private Long orderCount;
	}
}

/**
 * Scenario - 4 implementation
 * 
 * One-to-Many relationship.
 * 
 * @author KARTHIKEYAN N
 *	
 */
@Order(2)
@Log4j2
@Component
class QueryCustomersAndOrders implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	public QueryCustomersAndOrders(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		StringUtils.line();

		/**
		 * AGGREGATE
		 * 1, karthi, karthi@xyz.com, 1, abc
		 * 1, karthi, karthi@xyz.com, 2, def
		 * 1, karthi, karthi@xyz.com, 3, ghg
		 * AGGREGATE
		 * 2, pascal, pascal@xyz.com, 1, abc
		 * 2, pascal, pascal@xyz.com, 2, def
		 */
		
		ResultSetExtractor<Collection<Customer>> rse = new ResultSetExtractor<Collection<Customer>>() {

			@Override
			public Collection<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Map<Long, Customer> customerMap = new HashMap<>();
				Customer currentCustomer = null;
				
				while(rs.next()) {
					long id = rs.getLong("cid");
						
					if(currentCustomer == null || currentCustomer.getId() != id) {
						currentCustomer = new Customer(rs.getLong("cid"), rs.getString("name"), rs.getString("email"), new HashSet<>());
					}
					
					String customerFk = rs.getString("customer_fk");
					if(customerFk != null) {
						String sku = rs.getString("sku");
						Long oid = rs.getLong("oid");
						Order order = new Order(oid, sku);
						currentCustomer.getOrders().add(order);
					}
					customerMap.put(currentCustomer.getId(), currentCustomer);
				}
				
				return customerMap.values();
			}
			
		}; 

		String sql = "select c.id as cid, c.*, o.id as oid, o.* from customers c left join orders o on c.id = o.customer_fk order by cid";
		
		Collection<Customer> customers = this.jdbcTemplate.query(sql, rse);
		customers.forEach(log::info);
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String name, email;
		private Set<Order> orders = new HashSet<>();
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Order {
		private Long id;
		private String sku;
	}
	
}

/**
 * Scenario - 4 simple flat mapper implementation
 * 
 * One-to-Many relationship, using simple flat mapper lib
 * 
 * @author KARTHIKEYAN N
 *	
 */
@Order(3)
@Log4j2
@Component
class QueryCustomersAndOrdersSimpleFlatMapper implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	public QueryCustomersAndOrdersSimpleFlatMapper(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		StringUtils.line();

		ResultSetExtractorImpl<Customer> rse = 
				JdbcTemplateMapperFactory
				.newInstance()
				.addKeys("id")
				.newResultSetExtractor(Customer.class);
				

		String sql = "select c.id as id, c.name as name, c.email as email, o.id as orders_id, o.sku as orders_sku from customers c left join orders o on o.customer_fk = c.id order by c.id";
		
		Collection<Customer> customers = this.jdbcTemplate.query(sql, rse);
		
		//handle null orders
		customers = customers.stream().map(
				c -> {
					boolean hasNullOrderValues = c.getOrders().stream().anyMatch(o -> o.getId() == null);
					if(hasNullOrderValues) {
						c.setOrders(new HashSet<>());
					}
					return c;
				}).collect(Collectors.toSet());
		
		customers.forEach(log::info);
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String name, email;
		private Set<Order> orders = new HashSet<>();
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Order {
		private Long id;
		private String sku;
	}
	
}

/**
 * Scenario - 5 
 * How to insert a record using JDBC Template.?
 * How to retrieve the auto generated key? 
 * 
 * @author KARTHIKEYAN N
 *	
 */
@Configuration
@Order(4)
@Log4j2
class JdbcTemplateWriter implements ApplicationRunner {

	private final RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
		}
		
	};
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplateWriter(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Customer insert(String name, String email) {
		
		String sql = "insert into customers(name,email) values (?,?)";
		
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preparedStatement = 
						con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, email);
				return preparedStatement;
			}
		}, generatedKeyHolder);
		
		long idOfNewCustomer = generatedKeyHolder.getKey().longValue();
		
		//Retrieve inserted record using generatedKey
		return jdbcTemplate.queryForObject("select c.* from customers c where c.id = ?", customerRowMapper, idOfNewCustomer);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		StringUtils.line();
		Stream.of("A", "B", "C").forEach( name -> insert(name, name + "@xyz.com"));
		log.info("RESULTS");
		this.jdbcTemplate.query("select * from customers order by id", customerRowMapper).forEach(log::info);
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String name, email;
	}
}

/**
 * Scenario - 6 
 * How to use org.springframework.jdbc.object package classes?
 * 
 * This scenario-6 is similar to scenario-5. But, here, insert query
 * handled by using org.springframework.jdbc.object package classes.
 * 
 * @author KARTHIKEYAN N
 *	
 */
@Configuration
@Order(5)
@Log4j2
class JdbcObjectWriter implements ApplicationRunner {

	private JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert simpleJdbcInsert;
	
	public JdbcObjectWriter(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
		//step-1
		this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName("customers")
				.usingGeneratedKeyColumns("id");
		
		this.all = new CustomerMappingSqlQuery(this.jdbcTemplate.getDataSource(), "select * from customers");
		
		this.byId = new CustomerMappingSqlQuery(this.jdbcTemplate.getDataSource(), "select * from customers where id = ?", new SqlParameter("id", Types.INTEGER));
	}
	
	private static class CustomerMappingSqlQuery extends MappingSqlQuery<Customer> {
		
		public CustomerMappingSqlQuery(DataSource ds, String sql, SqlParameter...parameters) {
			setDataSource(ds);
			setSql(sql);
			setParameters(parameters);
			compile();
			//afterPropertiesSet(); //intern calls --> compile 
		}
		
		@Override
		protected Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
		}
	}
	
	private final CustomerMappingSqlQuery all, byId;
	
	public Customer insert(String name, String email) {
		Map<String, Object> params = new HashMap<>();	//here, map keys are sql table fields
		params.put("name", name);
		params.put("email", email);
		Long id = this.simpleJdbcInsert.executeAndReturnKey(params).longValue();
		return this.byId.findObject(id);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		StringUtils.line();
		Stream.of("D", "E", "F").forEach( name -> insert(name, name + "@xyz.com"));
		this.all.execute().forEach(log::info);
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String name, email;
	}
}

//==================================================================================

/**
 * Scenario - 7
 * How to use Spring data jdbc module? 
 * 
 * @author KARTHIKEYAN N
 *
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//class Customer {
//	@Id
//	private Long id;
//	private String name, email;
//}
//
//@Repository
//interface CustomerRepository extends CrudRepository<Customer, Long> {
//	
//	@Query("select * from customer c where c.email = :email")
//	Collection<Customer> findByEmail(@Param("email") String email);
//	
//}
//
//@Log4j2
//@Order(6)
//@Component
//class SpringDataJdbc implements ApplicationRunner {
//	
//	private final CustomerRepository customerRepository;
//	
//	public SpringDataJdbc(CustomerRepository customerRepository) {
//		this.customerRepository = customerRepository;
//	}
//
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		StringUtils.line();
//		Stream.of("G", "H", "I").forEach( name -> this.customerRepository.save(new Customer(null, name, name + "@xyz.com")));
//		this.customerRepository.findAll().forEach(log::info);
//		this.customerRepository.findByEmail("H@xyz.com").forEach(log::info);
//		
//	}
//	
//}
//
//@Configuration
//@EnableJdbcRepositories
//class SpringDataJdbcConfiguration {
//	
//	//Apply Naming Strategy
//	@Bean
//	NamingStrategy namingStrategy() {
//		
//		return new NamingStrategy() {
//
//			@Override
//			public String getTableName(Class<?> type) {
//				return type.getSimpleName().toLowerCase() + "s";
//			}
//			
//		};
//	}
//}


//==================================================================================

@Log4j2
abstract class StringUtils {
	public static void line() {
		log.info("============================");
	}
}
