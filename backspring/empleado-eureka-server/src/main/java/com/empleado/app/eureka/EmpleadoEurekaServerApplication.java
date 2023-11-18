package com.empleado.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EmpleadoEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadoEurekaServerApplication.class, args);
	}

}
