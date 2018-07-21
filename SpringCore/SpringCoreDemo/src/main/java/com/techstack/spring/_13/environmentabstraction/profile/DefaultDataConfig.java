/**
 * 
 */
package com.techstack.spring._13.environmentabstraction.profile;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@Profile("default")
public class DefaultDataConfig {
	
	@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:com/bank/config/sql/schema.sql")
            .build();
    }

}
