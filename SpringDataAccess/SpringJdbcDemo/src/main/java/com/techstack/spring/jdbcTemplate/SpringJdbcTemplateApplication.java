package com.techstack.spring.jdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@SpringBootApplication
public class SpringJdbcTemplateApplication {
	
	/**
	 * INFO: During application startup, Spring Boot will read all the application.properties 
	 * configuration and based on the, it will act accordingly.
	 * 
	 * For example, for development purpose, you have configured your schema.sql and data.sql in
	 * resources folder, configured Spring Boot as "spring.datasource.initialization-mode=always"
	 * then your DB will be dropped and recreated on each application startup.
	 * 
	 * This is not recommended for higher environment.
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(SpringJdbcTemplateApplication.class, args);
	}
}

/**
 * Scenario - 1 implementation
 * 
 * Simple query would return the total number of records available for the given table.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Order(1)
//@Component
class SimpleSelectUsingCount implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleSelectUsingCount(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		int rowCount = this.jdbcTemplate.queryForObject("select count(*) from customers", Integer.class);
		
		System.out.println("Total records in customers table is " + rowCount);
		
	}
}

/**
 * Scenario - 2 implementation
 * 
 * A simple query using a bind variable: query parameter (WHERE clause)
 * 
 * @author KARTHIKEYAN N
 *
 */
@Order(2)
//@Component
class SimpleSelectUsingBindVariable implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleSelectUsingBindVariable(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		int countOfActorsNamedKarthi = this.jdbcTemplate.queryForObject(
		        "select count(*) from customers where name = ?", Integer.class, "karthi");
		
		System.out.println("Name count " + countOfActorsNamedKarthi);
		
	}
	
}

/**
 * Scenario - 3 implementation
 * 
 * Querying for a String: query parameter (WHERE clause)
 * 
 * @author KARTHIKEYAN N
 *
 */
@Order(3)
//@Component
class SimpleQueryForString implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleQueryForString(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		String email = this.jdbcTemplate.queryForObject(
		        "select email from customers where id = ?", new Object[]{3}, String.class);
		
		System.out.println("email for the given customer id is " + email);
		
	}
	
}

/**
 * Scenario - 4 implementation
 * 
 * Querying and populating a single domain object: converting ResultSet to Customer domain object.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Order(4)
//@Component
class SimpleQueryAndPoulatingSingleDomainObject implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleQueryAndPoulatingSingleDomainObject(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		Customer customer = this.jdbcTemplate.queryForObject(
		        "select * from customers where id = ?",
		        new Object[]{3L},
		        
		        new RowMapper<Customer>() {
		        	
		            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Customer customer = new Customer();
		            	customer.setId(rs.getLong("id"));
		            	customer.setName(rs.getString("name"));
		                customer.setEmail(rs.getString("email"));
		                return customer;
		            }
		            
		        });
		
		System.out.println(customer.toString());
		
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
 * Scenario - 5 implementation
 * 
 * Querying and populating a number of domain objects: converting ResultSet to List<Customer> domain object.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Log4j2
@Order(5)
//@Component
class SimpleQueryAndPoulatingDomainObjectCollection implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleQueryAndPoulatingDomainObjectCollection(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		List<Customer> customers = this.jdbcTemplate.query(
		        "select * from customers",
		        new RowMapper<Customer>() {
		            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Customer customer = new Customer();
		            	customer.setId(rs.getLong("id"));
		            	customer.setName(rs.getString("name"));
		                customer.setEmail(rs.getString("email"));
		                return customer;
		            }
		        });
		
		customers.forEach(log::info);
		
		StringUtils.line();
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
 * Scenario - 6 implementation
 * 
 * Create custom RowMapper
 * 
 * If the last two snippet (Scenario 4 and 5) of code actually existed in the same application, it would make 
 * sense to remove the duplication present in the two RowMapper anonymous inner classes, and extract 
 * them out into a single class (typically a static nested class) that can then be referenced by DAO methods as needed.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Log4j2
@Order(6)
//@Component
class SimpleQueryDomainObjectUsingCustomRowMapper implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleQueryDomainObjectUsingCustomRowMapper(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		//Scenario - 4 modification
		Customer customer = this.jdbcTemplate.queryForObject(
		        "select * from customers where id = ?", new Object[]{3L}, new CustomerMapper());
		//System.out.println(customer.toString());
		log.info(customer);
		log.info("============");
		
		//Scenario - 5 modification
		List<Customer> customers = this.jdbcTemplate.query(
		        "select * from customers", new CustomerMapper());
		customers.forEach(log::info);
		
		StringUtils.line();
	}
	
	/**
	 * Create a  CustomerMapper class (Reusable) in many places.
	 * 
	 * @author KARTHIKEYAN N
	 *
	 */
	private static final class CustomerMapper implements RowMapper<Customer> {

	    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Customer customer = new Customer();
	    	customer.setId(rs.getLong("id"));
        	customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));
	        return customer;
	    }
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
 * Scenario - 7 implementation
 * 
 * Simple Insert, Update and Delete using JdbcTemplate
 * 
 * You use the update(..) method to perform insert, update and delete operations. Parameter values are 
 * usually provided as var args or alternatively as an object array.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Log4j2
@Order(7)
//@Component
class SimpleInsertUpdateDeleteOperation implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleInsertUpdateDeleteOperation(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		this.jdbcTemplate.update(
		        "insert into customers (name, email) values (?, ?)",
		        "vinoth", "vinoth@xyz.com");
		
		log.info("AFTER INSERT...");
		print();
		
		this.jdbcTemplate.update(
		        "update customers set email = ? where id = ?",
		        "vinoth@abc.com", 4L);
		
		log.info("AFTER UPDATE...");
		print();
		
		this.jdbcTemplate.update(
		        "delete from customers where id = ?", Long.valueOf(3));
		
		log.info("AFTER DELETE...");
		print();
		
	}
	
	private void print() {
		List<Customer> customers = this.jdbcTemplate.query(
		        "select * from customers", new CustomerMapper());
		customers.forEach(log::info);
	}
	
	/**
	 * Create a  CustomerMapper class (Reusable) in many places.
	 * 
	 * @author KARTHIKEYAN N
	 *
	 */
	private static final class CustomerMapper implements RowMapper<Customer> {

	    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Customer customer = new Customer();
	    	customer.setId(rs.getLong("id"));
        	customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));
	        return customer;
	    }
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
 * Scenario - 8 implementation
 * 
 * Other JdbcTemplate operations
 * 
 * You can use the execute(..) method to execute any arbitrary SQL, and as such the method is often used for DDL statements. 
 * It is heavily overloaded with variants taking callback interfaces, binding variable arrays, and so on.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Log4j2
@Order(8)
//@Component
class OtherJdbcTemplateOperation implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public OtherJdbcTemplateOperation(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		this.jdbcTemplate.execute("create table mytable (id integer, name varchar(100))");
		log.info("TABLE CREATED...");
		
		//The following example invokes a simple stored procedure.
		/**
		 *  DELIMITER //
			CREATE PROCEDURE UPDATE_CUSTOMER_EMAIL (IN customerId INT, IN customerEmail VARCHAR(50))
			BEGIN
				update customers set email=customerEmail where id=customerId;
			END//
			DELIMITER ;
		 */
		this.jdbcTemplate.update("call UPDATE_CUSTOMER_EMAIL(?, ?)", Long.valueOf(1), "karthi@abc.com");
		
		StringUtils.line();
	}
	
}

/**
 * Scenario - 9 implementation
 * 
 * Using NamedParameterJdbcTemplate
 *
 * The NamedParameterJdbcTemplate class adds support for programming JDBC statements using named parameters, as opposed 
 * to programming JDBC statements using only classic placeholder ( '?') arguments. The NamedParameterJdbcTemplate class 
 * wraps a JdbcTemplate, and delegates to the wrapped JdbcTemplate to do much of its work. This section describes only 
 * those areas of the NamedParameterJdbcTemplate class that differ from the JdbcTemplate itself; namely, programming JDBC 
 * statements using named parameters.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Log4j2
@Order(9)
//@Component
class UsingNamedParameterJdbcTemplate implements ApplicationRunner {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public UsingNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		/**
		 * Notice the use of the named parameter notation in the value assigned to the sql variable, and the 
		 * corresponding value that is plugged into the namedParameters variable (of type MapSqlParameterSource).
		 */
		String sql = "select count(*) from customers where name = :name";
		SqlParameterSource namedParameters = new MapSqlParameterSource("name", "karthi");
		
		int count = this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
		log.info("SqlParameterSource --> Total records for name karthi..{}", count);
		
		/**
		 * Alternatively, you can pass along named parameters and their corresponding values to a NamedParameterJdbcTemplate 
		 * instance by using the Map-based style.The remaining methods exposed by the NamedParameterJdbcOperations and 
		 * implemented by the NamedParameterJdbcTemplate class follow a similar pattern.
		 */
		Map<String, String> paramMap = Collections.singletonMap("name", "karthi");
		count = this.namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
		log.info("Map --> Total records for name karthi..{}", count);
		
		/**
		 * Another SqlParameterSource implementation is the BeanPropertySqlParameterSource class. This class wraps an 
		 * arbitrary JavaBean (that is, an instance of a class that adheres to the JavaBean conventions), and uses the 
		 * properties of the wrapped JavaBean as the source of named parameter values.
		 * 
		 * TIP: notice how the named parameters match the properties of the above 'Customer' class
		 */
		sql = "select count(*) from customers where name = :name and email = :email";
	    namedParameters = new BeanPropertySqlParameterSource(new Customer(null, "karthi", "karthi@xyz.com"));
	    count = this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
	    log.info("BeanPropertySqlParameterSource --> Total records for name karthi..{}", count);
	    
	    StringUtils.line();
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
 * Scenario - 10 implementation
 * 
 * - Using SQLExceptionTranslator and it's implementation SQLErrorCodeSQLExceptionTranslator.
 * - How to create custom SQLExceptionTranslator?
 * 
 * Why SQLExceptionTranslator?
 * 		SQLExceptionTranslator is an interface to be implemented by classes that can translate between SQLExceptions 
 * 		and Spring’s own org.springframework.dao.DataAccessException, which is agnostic in regard to data access strategy.
 * 
 * 		SQLErrorCodeSQLExceptionTranslator is the implementation of SQLExceptionTranslator that is used by default. 
 * 		This implementation uses specific vendor codes.
 * 
 * 		Spring uses SQLErrorCodes based on the contents of a configuration file named sql-error-codes.xml
 * 
 * 
 * @author KARTHIKEYAN N
 *
 */
@Order(10)
//@Component
class UsingSQLExceptionTranslator implements ApplicationRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UsingSQLExceptionTranslator(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		/**
		 * create a custom translator and set the DataSource for the default translation lookup
		 * 
		 * The custom translator is passed a data source in order to look up the error codes in sql-error-codes.xml.
		 */
	    CustomSQLErrorCodesTranslator tr = new CustomSQLErrorCodesTranslator();
	    tr.setDataSource(this.jdbcTemplate.getDataSource());
	    this.jdbcTemplate.setExceptionTranslator(tr);
		
		// use the prepared JdbcTemplate for this update
	    // ? -> Long(pct) and ? -> Long (id)
		this.jdbcTemplate.update("update orders" + " set shipping_charge = shipping_charge * ? / 100" + " where id = ?",
				12L, 3L);	//pct, orderId
	}
	
	/**
	 * You can extend SQLErrorCodeSQLExceptionTranslator to create your own custom translator.
	 * 
	 * In this example, the specific error code -12345 is translated and other errors are left to be translated by the 
	 * default translator implementation. To use this custom translator, it is necessary to pass it to the JdbcTemplate 
	 * through the method setExceptionTranslator and to use this JdbcTemplate for all of the data access processing where 
	 * this translator is needed. 
	 * 
	 * @author KARTHIKEYAN N
	 *
	 */
	public static class CustomSQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {

	    protected DataAccessException customTranslate(String task, String sql, SQLException sqlex) {
	        if (sqlex.getErrorCode() == -12345) {
	            return new DeadlockLoserDataAccessException(task, sqlex);
	        }
	        return null;
	    }
	}
	
}

/**
 * Scenario - 11 implementation
 * 
 * Executing statements
 * 		Executing an SQL statement requires very little code. You need a DataSource and a JdbcTemplate, 
 * including the convenience methods that are provided with the JdbcTemplate. The following example shows what you 
 * need to include for a minimal but fully functional class that creates a new table.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Order(11)
//@Component
class ExecuteAStatement implements ApplicationRunner {

	private JdbcTemplate jdbcTemplate;

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void doExecute() {
        this.jdbcTemplate.execute("create table mytable1 (id integer, name varchar(100))");
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		doExecute();
	}
}

/**
 * Scenario - 12 implementation
 * 
 * Running queries
 * 
 * 	- Some query methods return a single value. To retrieve a count or a specific value from one row, use queryForObject(..).
 * 
 * 	- In addition to the single result query methods, several methods return a list with an entry for each row that the query 
 * returned. The most generic method is queryForList(..) which returns a List where each entry is a Map with each entry in 
 * the map representing the column value for that row.
 * 
 * 
 * @author KARTHIKEYAN N
 *
 */
@Log4j2
@Order(12)
//@Component
class RunAQuery implements ApplicationRunner {

	private JdbcTemplate jdbcTemplate;

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from mytable", Integer.class);
    }

    public String getName() {
        return this.jdbcTemplate.queryForObject("select name from mytable", String.class);
    }
    
    /**
     * 
     * @return The list returned would look something like this: [{name=Bob, id=1}, {name=Mary, id=2}]
     */
    public List<Map<String, Object>> getList() {
        return this.jdbcTemplate.queryForList("select * from mytable");
    }
    
    /**
     * The following example shows a column updated for a certain primary key. In this example, an SQL statement 
     * has placeholders for row parameters. The parameter values can be passed in as varargs or alternatively as 
     * an array of objects. Thus primitives should be wrapped in the primitive wrapper classes explicitly or using auto-boxing.
     * 
     * @param id
     * @param name
     */
    public void setName(int id, String name) {
        this.jdbcTemplate.update("update mytable set name = ? where id = ?", name, id);
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		log.info(getCount());
		log.info(getName());
		log.info(getList());
		setName(1, "sara123");
		log.info("After modified");
		log.info(getList());
		
		StringUtils.line();
	}
}

/**
 * Scenario - 13 Retrieving auto-generated keys
 * 
 * How to retrieve the auto generated key?
 * 	- The method takes a PreparedStatementCreator as its first argument, and this is the way the required insert statement is specified.
 * 	- The other argument is a KeyHolder, which contains the generated key on successful return from the update.
 * 	- There is not a standard single way to create an appropriate PreparedStatement (which explains why the method signature is the way it is). 
 * 
 * @author KARTHIKEYAN N
 *	
 */
//@Configuration
@Order(13)
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
		Stream.of("Falk").forEach( name -> log.info(insert(name, name + "@xyz.com")));
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
 * Scenario - 14 JDBC batch operations
 * 
 * Basic batch operations with the JdbcTemplate
 * 		- You accomplish JdbcTemplate batch processing by implementing two methods of a special interface, 
 * BatchPreparedStatementSetter, and passing that in as the second parameter in your batchUpdate method call
 * 		- Use the getBatchSize method to provide the size of the current batch. Use the setValues method to set 
 * the values for the parameters of the prepared statement.
 * 		- This method will be called the number of times that you specified in the getBatchSize call. The following example 
 * updates the actor table based on entries in a list.
 * 
 * 
 * @author KARTHIKEYAN N
 *	
 */
//@Configuration
@Order(14)
@Log4j2
class BasicBatchOperationsWithJdbcTemplate implements ApplicationRunner {
	
	private final RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
		}
		
	};
	
	@Autowired
	private CustomerService customerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		StringUtils.line();
		
		List<Customer> customers = new LinkedList<>();
		customers.add(new Customer(null, "selva", "selva@xyz.com"));
		customers.add(new Customer(null, "suresh", "suresh@xyz.com"));
		customers.add(new Customer(null, "badri", "badri@xyz.com"));
		customerService.batchUpdate(customers);
		
		log.info("RESULTS");
		customerService.retieveAll().forEach(log::info);
		
		StringUtils.line();
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String name, email;
	}
	
	@Service
	public class CustomerService {
		
		@Autowired
		private CustomerDao customerDao;
		
		public int[] batchUpdate(final List<Customer> customers) {
			return customerDao.batchInsert(customers);
		}
		
		public List<Customer> retieveAll() {
			return customerDao.retieveAll();
		}
	}
	
	@Repository
	public class CustomerDao {
		
		@Autowired
		private JdbcTemplate jdbcTemplate;

//		@Autowired
//	    public void setDataSource(DataSource dataSource) {
//	        this.jdbcTemplate = new JdbcTemplate(dataSource);
//	    }
	    
		public int[] batchInsert(final List<Customer> customers) {
	        return this.jdbcTemplate.batchUpdate(
	                "insert into customers(name, email) values (?, ?)",
	                
	                /**
	                 * If you are processing a stream of updates or reading from a file, then you might have a 
	                 * preferred batch size, but the last batch might not have that number of entries. In this case you 
	                 * can use the InterruptibleBatchPreparedStatementSetter interface, which allows you to interrupt a 
	                 * batch once the input source is exhausted. The isBatchExhausted method allows you to signal the 
	                 * end of the batch.
	                 */
	                new BatchPreparedStatementSetter() {
	                    public void setValues(PreparedStatement ps, int i) throws SQLException {
	                        ps.setString(1, customers.get(i).getName());
	                        ps.setString(2, customers.get(i).getEmail());
	                    }
	                    public int getBatchSize() {
	                        return customers.size();
	                    }
	                });
	    }
		
		public List<Customer> retieveAll() {
			return this.jdbcTemplate.query("select * from customers order by id", customerRowMapper);
		}
	}
}

/**
 * <p>Scenario - 15 Batch operations with a List of objects</p>
 * 
 * Both the <code>JdbcTemplate</code> and the <code>NamedParameterJdbcTemplate</code> provides an alternate way of providing the batch update. 
 * Instead of implementing a special batch interface, you provide all parameter values in the call as a list. 
 * The framework loops over these values and uses an internal prepared statement setter. The API varies depending on 
 * whether you use named parameters. For the named parameters you provide an array of <code>SqlParameterSource</code>, 
 * one entry for each member of the batch. You can use the <code>SqlParameterSourceUtils.createBatch</code> convenience methods 
 * to create this array, passing in an array of bean-style objects (with getter methods corresponding to parameters) 
 * and/or String-keyed Maps (containing the corresponding parameters as values).
 * 
 * Refer: application.properties "JDBC Batch Operations"
 * 
 * @author KARTHIKEYAN N
 *	
 */
//@Configuration
@Order(15)
@Log4j2
class BatchOperationsWithListOfObjects implements ApplicationRunner {
	
	private final RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
		}
		
	};
	
	@Autowired
	private CustomerService customerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		StringUtils.line();
		
		log.info("BEFORE BATCH UPDATE");
		customerService.retieveAll().forEach(log::info);
		
		List<Customer> customers = new LinkedList<>();
		customers.add(new Customer(1L, "selva", "selva@xyz.com"));
		customers.add(new Customer(2L, "suresh", "suresh@xyz.com"));
		customers.add(new Customer(3L, "badri", "badri@xyz.com"));
		//customerService.batchUpdate(customers);
		int[] results = customerService.batchUpdateClassic(customers);
		log.info("Batch update results {}", Arrays.toString(results));
		
		log.info("====================");
		log.info("AFTER BATCH UPDATE");
		customerService.retieveAll().forEach(log::info);
		
		StringUtils.line();
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String name, email;
	}
	
	@Service
	public class CustomerService {
		
		@Autowired
		private CustomerDao customerDao;
		
		public int[] batchUpdate(final List<Customer> customers) {
			return customerDao.batchUpdate(customers);
		}
		
		public int[] batchUpdateClassic(final List<Customer> customers) {
			return customerDao.batchUpdateClassic(customers);
		}
		
		public List<Customer> retieveAll() {
			return customerDao.retieveAll();
		}
	}
	
	@Repository
	public class CustomerDao {
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//		@Autowired
//		public void setDataSource(DataSource dataSource) {
//	        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//	    }
	    
		public int[] batchUpdate(List<Customer> customers) {
	        return this.namedParameterJdbcTemplate.batchUpdate(
	                "update customers set name = :name, email = :email where id = :id",
	                SqlParameterSourceUtils.createBatch(customers));
	    }
		
		/**
		 * The previous method "batchUpdate" has been rewritten by using classic SQL statement.
		 * 
		 * For an SQL statement using the classic "?" placeholders, you pass in a list containing an 
		 * object array with the update values. This object array must have one entry for each placeholder 
		 * in the SQL statement, and they must be in the same order as they are defined in the SQL statement.
		 * 
		 * The same example using classic JDBC "?" placeholders:
		 * 
		 * <p>NOTE: All of the above batch update methods return an int array containing the number of 
		 * affected rows for each batch entry. This count is reported by the JDBC driver. If the count is not available, 
		 * the JDBC driver returns a -2 value.</p>
		 * 
		 * @param customers
		 * @return
		 */
		public int[] batchUpdateClassic(final List<Customer> customers) {
	        List<Object[]> batch = new ArrayList<Object[]>();
	        for (Customer customer : customers) {
	            Object[] values = new Object[] {
	                    customer.getName(), customer.getEmail(), customer.getId()};
	            batch.add(values);
	        }
	        return this.jdbcTemplate.batchUpdate(
	                "update customers set name = ?, email = ? where id = ?",
	                batch);
	    }
		
		public List<Customer> retieveAll() {
			return this.namedParameterJdbcTemplate.query("select * from customers order by id", customerRowMapper);
		}
	}
}

/**
 * <p>Scenario - 16 Batch operations with multiple batches</p>
 * 
 * The last example of a batch update deals with batches that are so large that you want to break them up into several smaller batches. 
 * You can of course do this with the methods mentioned above by making multiple calls to the batchUpdate method, but there is now 
 * a more convenient method. This method takes, in addition to the SQL statement, a Collection of objects containing the parameters, 
 * the number of updates to make for each batch and a ParameterizedPreparedStatementSetter to set the values for the parameters 
 * of the prepared statement. The framework loops over the provided values and breaks the update calls into batches of the size specified.
 * 
 * This example shows a batch update using a batch size of 100:
 * 
 * @author KARTHIKEYAN N
 *	
 */
//@Configuration
@Order(16)
@Log4j2
class BatchOperationsWithMultipleBatches implements ApplicationRunner {
	
	private final RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
		}
		
	};
	
	@Autowired
	private CustomerService customerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		StringUtils.line();
		
		log.info("BEFORE BATCH UPDATE");
		customerService.retieveAll().forEach(log::info);
		
		List<Customer> customers = new LinkedList<>();
		customers.add(new Customer(1L, "selva", "selva@xyz.com"));
		customers.add(new Customer(2L, "suresh", "suresh@xyz.com"));
		customers.add(new Customer(3L, "badri", "badri@xyz.com"));
		int[][] results = customerService.batchUpdate(customers);
		log.info("Batch update results {}", Arrays.toString(results));
		
		log.info("====================");
		log.info("AFTER BATCH UPDATE");
		customerService.retieveAll().forEach(log::info);
		
		StringUtils.line();
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String name, email;
	}
	
	@Service
	public class CustomerService {
		
		@Autowired
		private CustomerDao customerDao;
		
		public int[][] batchUpdate(final Collection<Customer> customers) {
			return customerDao.batchUpdate(customers);
		}
		
		public List<Customer> retieveAll() {
			return customerDao.retieveAll();
		}
	}
	
	@Repository
	public class CustomerDao {
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//		@Autowired
//		public void setDataSource(DataSource dataSource) {
//	        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//	    }
	    
		public int[][] batchUpdate(final Collection<Customer> customers) {
	        int[][] updateCounts = jdbcTemplate.batchUpdate(
	                "update customers set name = ?, email = ? where id = ?",
	                customers,
	                100,
	                new ParameterizedPreparedStatementSetter<Customer>() {
	                    public void setValues(PreparedStatement ps, Customer argument) throws SQLException {
	                        ps.setString(1, argument.getName());
	                        ps.setString(2, argument.getEmail());
	                        ps.setLong(3, argument.getId().longValue());
	                    }
	                });
	        return updateCounts;
	    }
		
		public List<Customer> retieveAll() {
			return this.namedParameterJdbcTemplate.query("select * from customers order by id", customerRowMapper);
		}
	}
}

/**
 * <p>Scenario - 17 Simplifying JDBC Operations with the SimpleJdbc classes</p>
 * 
 * <P>
 * - SimpleJdbcInsert class with the minimal amount of configuration options
 * - You should instantiate the SimpleJdbcInsert in the data access layer’s initialization method.
 * - simply create a new instance and set the table name using the withTableName method.
 * - This example uses only one configuration method
 * </p>
 * 
 * <p>
 * - The SimpleJdbcCall is declared in a similar manner to the SimpleJdbcInsert. 
 * - You should instantiate and configure the class in the initialization method of your data access layer.
 * - Compared to the StoredProcedure class, you don’t have to create a subclass and you don’t have to declare parameters 
 * that can be looked up in the database metadata.
 * - Following is an example of a SimpleJdbcCall configuration using the above stored procedure.
 * </p>
 * 
 * @author KARTHIKEYAN N
 *	
 */
//@Configuration
@Order(17)
@Log4j2
class InsertingDataUsingSimpleJdbc implements ApplicationRunner {
	
	private final RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
		}
		
	};
	
	@Autowired
	private CustomerService customerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		StringUtils.line();
		
		log.info("BEFORE SIMPLE JDBC INSERT");
		customerService.retieveAll().forEach(log::info);
		
		Customer customer = new Customer(100L, "selva", "selva@xyz.com");
		
		customerService.add(customer);
		customerService.addUsingAutoGenKey(new Customer(null, "selva", "selva@xyz.com"));
		customerService.addUsingColumns(customer);
		customerService.addUsingBeanPropertySqlParameterSource(customer);
		customerService.addUsingMapSqlParameterSource(customer);
		customer = customerService.readCustomer(2L);
		log.info(customer.toString());
		
		String customerName = customerService.getCustomerName(2L);
		
		customerService.getCustomersList().forEach(log::info);
		
		log.info("====================");
		log.info("AFTER SIMPLE JDBC INSERT");
		customerService.retieveAll().forEach(log::info);
		
		StringUtils.line();
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String name, email;
	}
	
	@Service
	public class CustomerService {
		
		@Autowired
		private CustomerDao customerDao;
		
		public void add(Customer customer) {
			customerDao.add(customer);
		}
		
		public void addUsingAutoGenKey(Customer customer) {
			customerDao.addUsingAutoGenKey(customer);
		}
		
		public void addUsingColumns(Customer customer) {
			customerDao.addUsingColumns(customer);
		}
		
		public void addUsingBeanPropertySqlParameterSource(Customer customer) {
			customerDao.addUsingBeanPropertySqlParameterSource(customer);
		}
		
		public void addUsingMapSqlParameterSource(Customer customer) {
			customerDao.addUsingMapSqlParameterSource(customer);
		}
		
		public Customer readCustomer(Long id) {
			return customerDao.readCustomer(id);
		}
		
		public String getCustomerName(Long id) {
			return customerDao.getCustomerName(id);
		}
		
		public List getCustomersList() {
			return customerDao.getCustomersList();
		}
		
		public List<Customer> retieveAll() {
			return customerDao.retieveAll();
		}
	}
	
	@Repository
	public class CustomerDao {
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
		private SimpleJdbcInsert insertCustomer;
		private SimpleJdbcInsert insertCustomerUsingAutoGenKey;
		private SimpleJdbcInsert insertCustomerUsingColumns;
		private SimpleJdbcCall procReadCustomer;
		private SimpleJdbcCall funcGetCustomerName;
		private SimpleJdbcCall procReadAllCustomers;
		
		@Autowired
		public void setDataSource(DataSource dataSource) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
			this.insertCustomer = new SimpleJdbcInsert(dataSource).withTableName("customers");
			
			this.insertCustomerUsingAutoGenKey = new SimpleJdbcInsert(dataSource)
														.withTableName("customers")
														.usingGeneratedKeyColumns("id");
			/**
			 * You can limit the columns for an insert by specifying a list of column names with the usingColumns method:
			 */
			this.insertCustomerUsingColumns = new SimpleJdbcInsert(dataSource)
								                .withTableName("customers")
								                .usingColumns("name", "email")
								                .usingGeneratedKeyColumns("id");
			
			this.procReadCustomer = new SimpleJdbcCall(dataSource).withProcedureName("read_customer");

			/**
			 * Alternate approach for new SimpleJdbcCall(dataSource) to handle result set names
			 * 
			 * To make your code more portable you should do a case-insensitive lookup or instruct Spring to use a 
			 * LinkedCaseInsensitiveMap. To do the latter, you create your own JdbcTemplate and set the setResultsMapCaseInsensitive 
			 * property to true. Then you pass this customized JdbcTemplate instance into the constructor of your SimpleJdbcCall.
			 */
//			jdbcTemplate.setResultsMapCaseInsensitive(true);
//			this.procReadCustomer = new SimpleJdbcCall(jdbcTemplate).withProcedureName("read_customer");
			
			/**
			 * Explicitly declaring parameters to use for a SimpleJdbcCall
			 * 
			 * You can opt to declare one, some, or all the parameters explicitly. The parameter metadata is still used where you do not declare 
			 * parameters explicitly. To bypass all processing of metadata lookups for potential parameters and only use the declared parameters, 
			 * you call the method withoutProcedureColumnMetaDataAccess as part of the declaration. Suppose that you have two or more different 
			 * call signatures declared for a database function. In this case you call the useInParameterNames to specify the list of IN parameter 
			 * names to include for a given signature.
			 * 
			 * This kind of configuration is not rely on metadata.
			 * 
			 */
//			this.procReadCustomer = new SimpleJdbcCall(jdbcTemplate)
//	                .withProcedureName("read_customer")
//	                .withoutProcedureColumnMetaDataAccess()
//	                .useInParameterNames("in_id")
//	                .declareParameters(
//	                		/**
//	                		 * SqlParameter declares an IN parameter. IN parameters can be used for both stored procedure calls 
//	                		 * and for queries using the SqlQuery and its subclasses
//	                		 */
//	                        new SqlParameter("in_id", Types.NUMERIC),
//	                        /**
//	                         * SqlOutParameter declares an out parameter to be used in a stored procedure call. There is also an SqlInOutParameter 
//	                         * for InOut parameters, parameters that provide an IN value to the procedure and that also return a value.
//	                         */
//	                        new SqlOutParameter("out_name", Types.VARCHAR),
//	                        new SqlOutParameter("out_email", Types.VARCHAR)
//	                );
			
			
			this.funcGetCustomerName = new SimpleJdbcCall(jdbcTemplate).withFunctionName("GET_CUSTOMER_NAME");
			
			this.procReadAllCustomers = new SimpleJdbcCall(jdbcTemplate)
							                .withProcedureName("READ_ALL_CUSTOMERS")
							                .returningResultSet("customers",
							                BeanPropertyRowMapper.newInstance(Customer.class));
	    }
	    
		/**
		 * The execute method used here takes a plain java.utils.Map as its only parameter. 
		 * The important thing to note here is that the keys used for the Map must match the column names of the table as defined in the database. 
		 * This is because we read the metadata in order to construct the actual insert statement.
		 * 
		 * @param customer
		 */
		public void add(Customer customer) {
	        Map<String, Object> parameters = new HashMap<String, Object>(3);
	        parameters.put("id", customer.getId());
	        parameters.put("name", customer.getName());
	        parameters.put("email", customer.getEmail());
	        insertCustomer.execute(parameters);
	    }
		
		/**
		 * The main difference when executing the insert by this second approach is that you do not add the id to the Map and you 
		 * call the executeAndReturnKey method. This returns a java.lang.Number object with which you can create an instance of the 
		 * numerical type that is used in our domain class. You cannot rely on all databases to return a specific Java class here; 
		 * java.lang.Number is the base class that you can rely on. If you have multiple auto-generated columns, or the generated 
		 * values are non-numeric, then you can use a KeyHolder that is returned from the executeAndReturnKeyHolder method.
		 * 
		 * @param customer
		 */
		public void addUsingAutoGenKey(Customer customer) {
	        Map<String, Object> parameters = new HashMap<String, Object>(2);
	        parameters.put("name", customer.getName());
	        parameters.put("email", customer.getEmail());
	        Number newId = insertCustomerUsingAutoGenKey.executeAndReturnKey(parameters);
	        customer.setId(newId.longValue());
	        log.info("Auto Generated Key for inserted Customer record {}", customer.getId());
	    }
		
		/**
		 * The execution of the insert is the same as if you had relied on the metadata to determine which columns to use.
		 * 
		 * @param customer
		 */
		public void addUsingColumns(Customer customer) {
	        Map<String, Object> parameters = new HashMap<String, Object>(2);
	        parameters.put("name", customer.getName());
	        parameters.put("email", customer.getEmail());
	        Number newId = insertCustomerUsingColumns.executeAndReturnKey(parameters);
	        customer.setId(newId.longValue());
	    }
		
		/**
		 * Using SqlParameterSource to provide parameter values
		 * 
		 * Using a Map to provide parameter values works fine, but it’s not the most convenient class to use. 
		 * Spring provides a couple of implementations of the <code>SqlParameterSource</code> interface that can be used instead.
		 * The first one is <code>BeanPropertySqlParameterSource</code>, which is a very convenient class if you have a JavaBean-compliant class that contains your values. 
		 * It will use the corresponding getter method to extract the parameter values. Here is an example:
		 * 
		 * @param customer
		 */
		public void addUsingBeanPropertySqlParameterSource(Customer customer) {
			SqlParameterSource parameters = new BeanPropertySqlParameterSource(customer);
	        Number newId = insertCustomerUsingAutoGenKey.executeAndReturnKey(parameters);
	        customer.setId(newId.longValue());
	        log.info("Auto Generated Key for inserted Customer record {}", customer.getId());
		}
		
		/**
		 * Different way to implement addUsingBeanPropertySqlParameterSource()
		 * 
		 * Another option is the MapSqlParameterSource that resembles a Map but provides a more convenient addValue method that can be chained.
		 * 
		 * As you can see, the configuration is the same; only the executing code has to change to use these alternative input classes.
		 * 
		 * @param customer
		 */
		public void addUsingMapSqlParameterSource(Customer customer) {
			SqlParameterSource parameters = new MapSqlParameterSource()
					.addValue("name", customer.getName())
	                .addValue("email", customer.getEmail());
	        Number newId = insertCustomerUsingAutoGenKey.executeAndReturnKey(parameters);
	        customer.setId(newId.longValue());
	        log.info("Auto Generated Key for inserted Customer record {}", customer.getId());
		}
		
		/**
		 * Using SimpleJdbcCall to call Stored Procedure.
		 * 
		 * The code you write for the execution of the call involves creating an SqlParameterSource containing the IN parameter. 
		 * It’s important to match the name provided for the input value with that of the parameter name declared in the stored procedure. 
		 * The case does not have to match because you use metadata to determine how database objects should be referred to in a 
		 * stored procedure. What is specified in the source for the stored procedure is not necessarily the way it is 
		 * stored in the database. Some databases transform names to all upper case while others use lower case or use the case as specified.
		 * 
		 * The execute method takes the IN parameters and returns a Map containing any out parameters keyed by the name as specified 
		 * in the stored procedure. In this case they are out_name, out_email.
		 * 
		 * @param id
		 * @return
		 */
		public Customer readCustomer(Long id) {
	        SqlParameterSource in = new MapSqlParameterSource()
	                .addValue("in_id", id);
	        Map<String, Object> out = procReadCustomer.execute(in);
	        Customer customer = new Customer();
	        customer.setId(id);
	        customer.setName((String) out.get("out_name"));
	        customer.setEmail((String) out.get("out_email"));
	        return customer;
	    }
		
		/**
		 * Calling a stored function using SimpleJdbcCall
		 * 
		 * To call this function we again create a SimpleJdbcCall in the initialization method.
		 * 
		 * CREATE FUNCTION GET_CUSTOMER_NAME (in_id INTEGER)
			RETURNS VARCHAR(200) READS SQL DATA
			BEGIN
			    DECLARE out_name VARCHAR(200);
			    SELECT name
			        INTO out_name
			        FROM customers where id = in_id;
			    RETURN out_name;
			END;
		 * 
		 * @param id
		 * @return
		 */
		public String getCustomerName(Long id) {
	        SqlParameterSource in = new MapSqlParameterSource()
	                .addValue("in_id", id);
	        String name = this.funcGetCustomerName.executeFunction(String.class, in);
	        return name;
	    }
		
		/**
		 * Returning ResultSet/REF Cursor from a SimpleJdbcCall
		 * 
		 * -Calling a stored procedure or function that returns a result set is a bit tricky. 
		 * -Some databases return result sets during the JDBC results processing while others require an explicitly registered out parameter of a specific type. 
		 * -Both approaches need additional processing to loop over the result set and process the returned rows.
		 * -With the SimpleJdbcCall you use the returningResultSet method and declare a RowMapper implementation to be used for a specific parameter.
		 * -In the case where the result set is returned during the results processing, there are no names defined, so the returned results will have to 
		 * match the order in which you declare the RowMapper implementations.
		 * -The name specified is still used to store the processed list of results in the results map that is returned from the execute statement.
		 * 
		 * <code>
		 * 		DELIMITER //
				CREATE PROCEDURE read_all_customers()
					BEGIN
					 SELECT c.id, c.name, c.email FROM customers c;
				END//
				DELIMITER ;
		 * </code>
		 * 
		 * 
		 * 
		 * @return
		 */
		public List getCustomersList() {
	        Map m = procReadAllCustomers.execute(new HashMap<String, Object>(0));
	        return (List) m.get("customers");
	    }
		
		public List<Customer> retieveAll() {
			return this.jdbcTemplate.query("select * from customers order by id", customerRowMapper);
		}
	}
}

/**
 * <p>Scenario - 18 Modeling JDBC operations as Java objects</p>
 * 
 * <p>
 * - The org.springframework.jdbc.object package contains classes that allow you to access the database in a more object-oriented manner.
 * - As an example, you can execute queries and get the results back as a list containing business objects with the relational column 
 * data mapped to the properties of the business object. You can also execute stored procedures and run update, delete, and insert statements.  
 * </p>
 * 
 * <code>
 * 		SqlQuery 		- superclass
 * 		MappingSqlQuery - subclass
 * 		MappingSqlQueryWithParameters - subclass
 * 		UpdatableSqlQuery - subclass 
 * </code>
 * 
 * @author KARTHIKEYAN N
 *	
 */
@Configuration
@Order(18)
@Log4j2
class ModelingJdbcOperationsUsingJavaObjects implements ApplicationRunner {
	
	@Autowired
	private CustomerService customerService;
	
	public void run(ApplicationArguments args) throws Exception {
		
		StringUtils.line();
		
		log.info("BEFORE SIMPLE JDBC INSERT");
		Customer customer = customerService.getCustomer(3L);
		log.info(customer.toString());
		
		customerService.searchForCustomers("karthi");
		customerService.updateCustomerEmail(3L, "test@xyz.com");
		customerService.getSysdate();
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String name, email;
	}

	public class CustomerMappingQuery extends MappingSqlQuery<Customer> {

	    public CustomerMappingQuery(DataSource ds) {
	        super(ds, "select id, name, email from customers where id = ?");
	        declareParameter(new SqlParameter("id", Types.INTEGER));
	        compile();
	    }

	    @Override
	    protected Customer mapRow(ResultSet rs, int rowNumber) throws SQLException {
	    	Customer customer = new Customer();
	        customer.setId(rs.getLong("id"));
	        customer.setName(rs.getString("name"));
	        customer.setEmail(rs.getString("email"));
	        return customer;
	    }
	}
	
	/**
	 * The SqlUpdate class encapsulates an SQL update. Like a query, an update object is reusable, and like all RdbmsOperation classes, 
	 * an update can have parameters and is defined in SQL. This class provides a number of update(..) methods analogous to the execute(..) 
	 * methods of query objects. The SQLUpdate class is concrete. It can be subclassed, for example, to add a custom update method, as in the 
	 * following snippet where it’s simply called execute. However, you don’t have to subclass the SqlUpdate class since it can easily be 
	 * parameterized by setting SQL and declaring parameters.
	 * 
	 * @author KARTHIKEYAN N
	 *
	 */
	public class UpdateCreditRating extends SqlUpdate {

	    public UpdateCreditRating(DataSource ds) {
	        setDataSource(ds);
	        setSql("update customer set email = ? where id = ?");
	        declareParameter(new SqlParameter("email", Types.VARCHAR));
	        declareParameter(new SqlParameter("id", Types.NUMERIC));
	        compile();
	    }

	    /**
	     * @param id for the Customer to be updated
	     * @param rating the new value for credit rating
	     * @return number of rows updated
	     */
	    public int execute(Long id, String email) {
	        return update(email, id);
	    }
	}
	
	/**
	 * Here is an example that uses a StoredProcedure to call a function, sysdate(), which comes with any Oracle database. 
	 * To use the stored procedure functionality you have to create a class that extends StoredProcedure. In this example, 
	 * the StoredProcedure class is an inner class, but if you need to reuse the StoredProcedure you declare it as a top-level class. 
	 * This example has no input parameters, but an output parameter is declared as a date type using the class SqlOutParameter. 
	 * The execute() method executes the procedure and extracts the returned date from the results Map. The results Map has an entry 
	 * for each declared output parameter, in this case only one, using the parameter name as the key.
	 * 
	 * @author KARTHIKEYAN N
	 *
	 */
	private class GetSysdateProcedure extends StoredProcedure {

        private static final String SQL = "sysdate";

        public GetSysdateProcedure(DataSource dataSource) {
            setDataSource(dataSource);
            setFunction(true);
            setSql(SQL);
            declareParameter(new SqlOutParameter("date", Types.DATE));
            compile();
        }

        public Date execute() {
            // the 'sysdate' sproc has no input parameters, so an empty Map is supplied...
            Map<String, Object> results = execute(new HashMap<String, Object>());
            Date sysdate = (Date) results.get("date");
            return sysdate;
        }
    }
	
	@Service
	public class CustomerService {
		
		@Autowired
		private CustomerDao customerDao;
		
		public Customer getCustomer(Long id) {
			return customerDao.getCustomer(id);
		}
		
		public List<Customer> searchForCustomers(String namePattern) {
			return customerDao.searchForCustomers(namePattern);
		}
		
		public int updateCustomerEmail(Long id, String email) {
			return customerDao.updateCustomerEmail(id, email);
		}
		
		public Date getSysdate() {
			return customerDao.getSysdate();
		}
	}
	
	@Repository
	public class CustomerDao {
		
		private CustomerMappingQuery customerMappingQuery;
		private UpdateCreditRating updateCreditRating;
		private GetSysdateProcedure getSysdateProcedure;
		
		@Autowired
		public void setDataSource(DataSource dataSource) {
			this.customerMappingQuery = new CustomerMappingQuery(dataSource);
			this.updateCreditRating = new UpdateCreditRating(dataSource);
			this.getSysdateProcedure = new GetSysdateProcedure(dataSource);
		}
		
		/**
		 * - The method in this example retrieves the customer with the id that is passed in as the only parameter. 
		 * Since we only want one object returned we simply call the convenience method findObject with the id as parameter.
		 * 
		 * @param id
		 * @return
		 */
		public Customer getCustomer(Long id) {
		    return this.customerMappingQuery.findObject(id);
		}
		
		/**
		 * - If we had instead a query that returned a list of objects and took additional parameters then we would use one of the
		 * execute methods that takes an array of parameter values passed in as varargs.
		 * 
		 * @param age
		 * @param namePattern
		 * @return
		 */
		public List<Customer> searchForCustomers(String namePattern) {
		    List<Customer> customers = this.customerMappingQuery.execute(namePattern);
		    return customers;
		}
		
		/**
		 * Using SqlUpdate
		 * 
		 * @param id
		 * @param email
		 * @return
		 */
		public int updateCustomerEmail(Long id, String email) {
			return this.updateCreditRating.execute(id, email);
		}
		
		public Date getSysdate() {
	        return this.getSysdateProcedure.execute();
	    }
	}
}
