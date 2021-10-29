package com.resources;
/**
 * @author SumanD
 *
 */
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.resources.model.Available;
import com.resources.model.Workers;
import com.resources.service.IWorkersService;

@SpringBootApplication
@EnableEurekaClient
public class TripResourcesMicroservicesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TripResourcesMicroservicesApplication.class, args);
	}

	@Autowired
	IWorkersService workersService;

	@Override
	public void run(String... args) throws Exception {
		Workers worker1 = new Workers("suman", "Senior-technician", LocalDate.of(2021, 3, 15),
				LocalDate.of(2021, 3, 18), Available.AVAILABLE,"");
		Workers worker2 = new Workers("prasanna", "technician", LocalDate.of(2021, 4, 10), LocalDate.of(2021, 4, 20),
				Available.NOTAVAILABLE,"");
		workersService.addWorkers(worker1);
		workersService.addWorkers(worker2);

	}

}
