package com.empleado.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;

@EnableEurekaClient
@SpringBootApplication
public class EmpleadoGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadoGatewayServerApplication.class, args);
	}
	
	/*
	 * @Bean public WebMvcC autoConfiguration() { return new
	 * WebMvcAutoConfiguration() { public void addCorsMappings(CorsRegistry
	 * registry) {
	 * registry.addMapping("/**").allowedOrigins("/**").allowedMethods("*").
	 * allowedHeaders("*"); } }; }
	 */

}
