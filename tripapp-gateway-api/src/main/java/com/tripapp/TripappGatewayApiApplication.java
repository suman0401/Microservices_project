package com.tripapp;
/**
 * @author SumanD
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class TripappGatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripappGatewayApiApplication.class, args);
	}

	@Bean
	public RouteLocator customRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("workerAPI", r->
				r.path("/workers-service/**")
				.uri("lb://WORKERS-SERVICE"))
				.route("tripAPI", r->
				r.path("/trip-service/**")
				.uri("lb://TRIP-SERVICE"))
				.route("taskAPI", r->
				r.path("/task-service/**")
				.uri("lb://TASK-SERVICE"))
				.route("maintainenceAPI", r->
				r.path("/maintenence-service/**")
				.uri("lb://MAINTENENCE-SERVICE"))
				.build();
	}
}
