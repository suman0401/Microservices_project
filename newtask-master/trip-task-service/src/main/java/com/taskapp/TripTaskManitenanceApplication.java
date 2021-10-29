package com.taskapp;

/**
 * @author SathyaPriyanaka
 *
 */
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.taskapp.model.Maintenence;
import com.taskapp.model.Priority;
import com.taskapp.model.Status;
import com.taskapp.model.Task;
import com.taskapp.model.Trip;
import com.taskapp.service.ITaskService;

@SpringBootApplication
@EnableEurekaClient

public class TripTaskManitenanceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TripTaskManitenanceApplication.class, args);
	}

	@Autowired
	ITaskService taskService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		

	}

	@Bean

	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
