package com.mainteneceapp;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.mainteneceapp.model.Maintenence;
import com.mainteneceapp.model.Priority;
import com.mainteneceapp.model.Status;
import com.mainteneceapp.service.IMaintenenceService;

@SpringBootApplication
@EnableDiscoveryClient
public class TripappProjectRestapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TripappProjectRestapiApplication.class, args);
	}

	@Autowired
	IMaintenenceService maintenenceServiceImpl;
	
	@Override
	public void run(String... args) throws Exception {
		
	}
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
