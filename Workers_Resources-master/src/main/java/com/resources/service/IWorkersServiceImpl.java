package com.resources.service;
/**
 * @author SumanD
 *
 */
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resources.model.Available;
import com.resources.model.Workers;
import com.resources.repository.IWorkersRepository;

@Service
public class IWorkersServiceImpl implements IWorkersService {
	@Autowired
	IWorkersRepository workersRepository;



	@Override
	public List<Workers> getallWorkers() {
		return workersRepository.findAll();
	}

	@Override
	public Workers getById(int workersId) {
		return workersRepository.findById(workersId).get();
	}

	@Override
	public List<Workers> getByWorkersName(String workersName) {
		return workersRepository.findByWorkersName(workersName);
	}

	@Override
	public List<Workers> getByWorkersType(String workersType) {
		return workersRepository.findByWorkersType(workersType);
	}

	@Override
	public List<Workers> getByWorkersAvailableFrom(LocalDate workersavailablefrom) {
		return workersRepository.findByWorkersavailablefrom(workersavailablefrom);
	}

	@Override
	public List<Workers> getByWorkersAvailability(Available availability) {
		return workersRepository.findByAvailability(availability);
	}

	@Override
	public Workers addWorkers(Workers workers) {
		return workersRepository.save(workers);
	}

	@Override
	public void assignWorkersToTask(int taskId, int workersId) {
		workersRepository.assignWorkersToTask(taskId, workersId);
		
	}

	@Override
	public void removeWorkersToTask(int taskId, int workersId) {
		workersRepository.removeWorkersToTask(taskId);
		
	}



}
