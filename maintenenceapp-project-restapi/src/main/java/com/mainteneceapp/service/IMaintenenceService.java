package com.mainteneceapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mainteneceapp.exceptions.MaintenenceNotFoundException;
import com.mainteneceapp.model.Maintenence;
import com.mainteneceapp.model.Priority;
import com.mainteneceapp.model.Status;
import com.mainteneceapp.model.Task;

/**
 * 
 * @author ManikantaJV
 *
 */
public interface IMaintenenceService {
	
	public Maintenence addMaintenence(Maintenence maintenece);
	public Maintenence updateMaintenence(Maintenence maintenence) throws MaintenenceNotFoundException;
	public void deleteMaintenence(int maintenenceId) throws MaintenenceNotFoundException;
	
	public List<Maintenence> getAllMaintenences();
	public Maintenence getById(int projectId) throws MaintenenceNotFoundException;
	public Maintenence getMaintenencesByName(String name) throws MaintenenceNotFoundException;
	public List<Maintenence> getMaintenenceByOwner(String owner) throws MaintenenceNotFoundException; 
	public List<Maintenence> getMaintenenceByStartDate(LocalDate startDate) throws MaintenenceNotFoundException; 
	public List<Maintenence> getMaintenenceByEndDate(LocalDate endDate) throws MaintenenceNotFoundException;
	public List<Maintenence> getMaintenenceByPriority(Priority priority) throws MaintenenceNotFoundException;
	public List<Maintenence> getMaintenenceByOwnerAndPriority(String owner, Priority priority) throws MaintenenceNotFoundException; 
	
	public List<Maintenence> getMainteneceByTrip(String tripName);
	
	public List<Maintenence> getMaintenenceByPriorityAndTrip(Priority priority, String tripName);
	
	public List<Maintenence> getMainteneceByTripId(int tripId);
	
	public int updateMaintenenceStatus(Status status, int maintenenceId);
	
	public ResponseEntity<Task> getTaskById(int taskId);
	public ResponseEntity<List> getAllTasks(); 
	public ResponseEntity<String> assignTask(int maintenenceId, int taskId);
	
	public String addTask(Task task,int maintenenceId);
	
	public String assingTasktoMaintenence(int maintenenceId, int taskId);
}
