package com.resources.service;
/**
 * @author SumanD
 *
 */
import java.time.LocalDate;
import java.util.List;

import com.resources.model.Available;
import com.resources.model.Workers;


public interface IWorkersService {
	Workers addWorkers(Workers workers);
	
	List<Workers> getallWorkers();
	Workers getById(int workersId);
	List<Workers> getByWorkersName(String workersName);
	List<Workers> getByWorkersType(String workersType);
	List<Workers> getByWorkersAvailability(Available availability);
	List<Workers> getByWorkersAvailableFrom(LocalDate workersavailablefrom);
	
	void assignWorkersToTask(int taskId, int workersId);
	void removeWorkersToTask(int taskId, int workersId);

}
