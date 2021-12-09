package com.certant.vtvSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;






@SpringBootApplication()//exclude= {DataSourceAutoConfiguration.class})
//@ComponentScan({"com.delivery.request"})
@EntityScan("com.certant.vtvSpringBoot.domain")
@EnableJpaRepositories("com.certant.vtvSpringBoot.repositories")
public class VtvSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(VtvSpringBootApplication.class, args);
	}

}
