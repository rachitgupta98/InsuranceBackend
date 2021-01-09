package com.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.insurance" })
@EntityScan(basePackages = "com.insurance")
public class InsuranceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceBackendApplication.class, args);
	}

}
