package com.birdeye.employee_details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import com.birdeye.employeeRepo.EmployeeRepo;

@SpringBootApplication
//@ComponentScan(basePackages="EmployeeRepo.class")
//@EnableJpaRepositories("com.birdeye.employeeRepo")
@EnableCaching
public class Employees_main {

	public static void main(String[] args) {
		SpringApplication.run(Employees_main.class, args);
	}

}
