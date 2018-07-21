//package com.techstack.spring.jdbcTemplate;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.core.annotation.Order;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import com.techstack.spring.jdbcTemplate.UsingSQLExceptionTranslator.CustomSQLErrorCodesTranslator;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
///**
// * 
// * @author KARTHIKEYAN N
// *
// */
//@SpringBootApplication
//public class SpringJdbcTemplateBestPractices {
//	
//	public static void main(String[] args) {
//		
//		SpringApplication.run(SpringJdbcTemplateBestPractices.class, args);
//	}
//}
//
///**
// * Scenario - 1 implementation
// * 
// * Instances of the JdbcTemplate class are threadsafe once configured. This is important because it means that you 
// * can configure a single instance of a JdbcTemplate and then safely inject this shared reference into multiple DAOs 
// * (or repositories). The JdbcTemplate is stateful, in that it maintains a reference to a DataSource, but this state 
// * is not conversational state.
// * 
// * @author KARTHIKEYAN N
// *
// */
//@Order(1)
//@Component
//class JdbcTemplateBestPractices implements ApplicationRunner {
//
//	@Autowired
//	private CustomerService customerService;
//
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		
//		StringUtils.line();
//		Customer customer = customerService.fetchCustomer("karthi@xyz.com");
//		customer.toString();
//		
//	}
//	
//}
//
//@Service
//class CustomerService {
//	
//	@Autowired
//	private CustomerDao customerDao;
//	
//	public Customer fetchCustomer(String email) {
//		return customerDao.fetchCustomer(email);
//	}
//	
//}
//
///**
// * 
// * A common practice when using the JdbcTemplate class (and the associated NamedParameterJdbcTemplate classes) is to 
// * configure a DataSource in your Spring configuration file, and then dependency-inject that shared DataSource bean into 
// * your DAO classes; the JdbcTemplate is created in the setter for the DataSource. This leads to DAOs that look in part 
// * like the following:
// * 
// * @author KARTHIKEYAN N
// *
// */
//@Repository
//class CustomerDao {
//	
//	private JdbcTemplate jdbcTemplate;
//	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//	@Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);  //<==This JdbcTemplate
//        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource); //<==This NamedJdbcTemplate
//        
//        // create a custom translator and set the DataSource for the default translation lookup
//        CustomSQLErrorCodesTranslator tr = new CustomSQLErrorCodesTranslator();	//<==For SQLExceptionTranslator
//        tr.setDataSource(dataSource);
//        this.jdbcTemplate.setExceptionTranslator(tr);
//    }
//    
//    public Customer fetchCustomer(String email) {
//    	Customer customer = this.jdbcTemplate.queryForObject(
//		        "select * from customers where id = ?", new Object[]{3L}, new CustomerMapper());
//		return customer;
//    }
//    
//	public void updateShippingCharge(long orderId, long pct) {
//		// use the prepared JdbcTemplate for this update
//		this.jdbcTemplate.update("update orders set shipping_charge = shipping_charge * ? / 100 where id = ?",
//				pct, orderId);
//	}
//    
//    /**
//	 * Create a  CustomerMapper class (Reusable) in many places.
//	 * 
//	 * @author KARTHIKEYAN N
//	 *
//	 */
//	private static final class CustomerMapper implements RowMapper<Customer> {
//
//	    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
//	    	Customer customer = new Customer();
//	    	customer.setId(rs.getLong("id"));
//        	customer.setName(rs.getString("name"));
//            customer.setEmail(rs.getString("email"));
//	        return customer;
//	    }
//	}
//}
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Component
//class Customer {
//	private Long id;
//	private String name, email;
//	private Set<Order> orders = new HashSet<>();
//}
//
