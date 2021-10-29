package com.mainteneceapp.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mainteneceapp.exceptions.MaintenenceNotFoundException;
import com.mainteneceapp.model.Maintenence;
import com.mainteneceapp.model.Priority;
import com.mainteneceapp.model.Status;
import com.mainteneceapp.model.Task;
import com.mainteneceapp.repo.IMaintenenceRepo;

@Service
/**
 * 
 * @author ManikantaJV
 *
 */
public class MainteneceServiceImpl implements IMaintenenceService {

	@Autowired
	IMaintenenceRepo maintenenceRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String BASEURL="http://localhost:8082/task-service";
	
	@Override
	public List<Maintenence> getAllMaintenences() {
		return maintenenceRepo.findAll();
	}

	@Override
	public Maintenence getById(int projectId) throws MaintenenceNotFoundException {
		return maintenenceRepo.findById(projectId)
				.orElseThrow(() -> new MaintenenceNotFoundException("Maintenence with Id " +projectId+ " Not Found"));
	}

	@Override
	public Maintenence getMaintenencesByName(String name) throws MaintenenceNotFoundException{
		Maintenence maintenence = maintenenceRepo.findByMaintenenceName(name);
		if(maintenence == null) {
			throw new MaintenenceNotFoundException("name not found");
		}
		return maintenence;
	}

	@Override
	public Maintenence addMaintenence(Maintenence maintenence) {
		return maintenenceRepo.save(maintenence);
	}

	@Override
	public Maintenence updateMaintenence(Maintenence maintenence) {
		return maintenenceRepo.save(maintenence);
	}

	@Override
	public void deleteMaintenence(int maintenenceId) throws MaintenenceNotFoundException {
		Maintenence maintenence = maintenenceRepo.findById(maintenenceId).
				orElseThrow(() -> new MaintenenceNotFoundException("maintenenceId nof found"));
		maintenenceRepo.deleteById(maintenenceId);
	}
	
	
	public ResponseEntity<Task> getTaskById(int taskId) {
		String url = BASEURL + "/task/" + taskId; 
		return restTemplate.getForEntity(url, Task.class);
	}

	@Override
	public ResponseEntity<List> getAllTasks() {
		String url = BASEURL + "/task";
		return restTemplate.getForEntity(url, List.class);
	}

	@Override
	public ResponseEntity<String> assignTask(int maintenenceId, int taskId) {
		String url = BASEURL + "/task/assigntask/maintenenceId/"+maintenenceId+"/taskId/"+taskId;
		return restTemplate.getForEntity(url, String.class);
	}

	@Override
	public String addTask(Task task, int maintenenceId) {
		Maintenence maintenence = maintenenceRepo.findById(maintenenceId).get();
		maintenence.setTaskList(new HashSet<>(Arrays.asList(task)));
		maintenenceRepo.save(maintenence);
		return "Task added";
	}

	@Override
	public List<Maintenence> getMaintenenceByOwner(String owner) throws MaintenenceNotFoundException {
		List<Maintenence> maintenenceList = maintenenceRepo.findByMaintenenceOwner(owner);
		if(maintenenceList.isEmpty()) {
			throw new MaintenenceNotFoundException("Owner not found");
		}
		return maintenenceList;
	}

	@Override
	public List<Maintenence> getMaintenenceByStartDate(LocalDate startDate) throws MaintenenceNotFoundException {
		List<Maintenence> maintenenceList = maintenenceRepo.findByMaintenenceStartDate(startDate);
		if(maintenenceList.isEmpty()) {
			throw new MaintenenceNotFoundException("No Maintenence found with start date " + startDate);
		}
		return maintenenceList;
	}

	@Override
	public List<Maintenence> getMaintenenceByEndDate(LocalDate endDate) throws MaintenenceNotFoundException {
		List<Maintenence> maintenenceList = maintenenceRepo.findByMaintenenceEndDate(endDate);
		if(maintenenceList.isEmpty()) {
			throw new MaintenenceNotFoundException("No maintenence found with endDate " + endDate);
		}
		return maintenenceList;
	}

	@Override
	public List<Maintenence> getMaintenenceByPriority(Priority priority) throws MaintenenceNotFoundException {
			return maintenenceRepo.findByMaintenencePriority(priority);
	}

	@Override
	public String assingTasktoMaintenence(int maintenenceId, int taskId)throws MaintenenceNotFoundException {
		Maintenence maintenence = maintenenceRepo.findById(maintenenceId)
				.orElseThrow(() -> new MaintenenceNotFoundException("Id not found")); 
		String url = BASEURL + "/task/" + taskId;
		ResponseEntity<Task> task1 = restTemplate.getForEntity(url, Task.class);
		Task task = task1.getBody();
		maintenence.setTaskList(new HashSet<>(Arrays.asList(task)));
		maintenenceRepo.save(maintenence);
		return "Task assigned to maintenence";
	}

	@Override
	public List<Maintenence> getMaintenenceByOwnerAndPriority(String owner, Priority priority)
			throws MaintenenceNotFoundException {
		List<Maintenence> maintenenceList = maintenenceRepo.findMaintenenceByOwnerAndPriority(owner, priority);
		if(maintenenceList.isEmpty()) {
			throw new MaintenenceNotFoundException("maintenence not found");
		}
		return maintenenceList;
	}

	@Override
	public List<Maintenence> getMainteneceByTrip(String tripName) {
		return maintenenceRepo.findMainteneceByTrip(tripName);
	}

	@Override
	public List<Maintenence> getMaintenenceByPriorityAndTrip(Priority priority, String tripName) {
		return maintenenceRepo.findMaintenenceByPriorityAndTrip(priority, tripName);
	}

	@Override
	public List<Maintenence> getMainteneceByTripId(int tripId) {
		return maintenenceRepo.findMainteneceByTripId(tripId);
	}

	@Override
	public int updateMaintenenceStatus(Status status, int maintenenceId) {
		return maintenenceRepo.updateMaintenenceStatus(status, maintenenceId);
	}
	
	
}
