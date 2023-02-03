package org.connector.e2etestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class E2etestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(E2etestserviceApplication.class, args);
	}

}
