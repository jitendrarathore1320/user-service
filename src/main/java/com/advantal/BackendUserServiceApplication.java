package com.advantal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class BackendUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendUserServiceApplication.class, args);
		System.out.println(".....RUN sucessfully....");
	}

}
