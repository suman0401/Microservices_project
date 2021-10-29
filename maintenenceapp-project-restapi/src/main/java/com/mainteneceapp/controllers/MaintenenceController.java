package com.mainteneceapp.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainteneceapp.exceptions.MaintenenceNotFoundException;
import com.mainteneceapp.model.Maintenence;
import com.mainteneceapp.model.Priority;
import com.mainteneceapp.model.Status;
import com.mainteneceapp.model.Task;
import com.mainteneceapp.service.IMaintenenceService;

@RestController
@RequestMapping("/maintenence-service")
/**
 * 
 * @author ManikantaJV
 *
 */
public class MaintenenceController {

	@Autowired
	IMaintenenceService maintenenceService;

	/**
	 * 
	 * @param maintenence
	 * @description adds maintenance to database
	 * 
	 */
	@PostMapping("/maintenence")
	public ResponseEntity<Maintenence> addMaintenence(@RequestBody Maintenence maintenence) {
		return ResponseEntity.ok(maintenenceService.addMaintenence(maintenence));
	}

	/**
	 * 
	 * @param maintenence
	 * @description updates maintenance
	 * 
	 */
	@PutMapping("/maintenence")
	public Maintenence updateMaintenence(@RequestBody Maintenence maintenence) {
		return maintenenceService.updateMaintenence(maintenence);
	}

	/**
	 * 
	 * @param maintenenceId
	 * @description removes maintenance from database
	 */
	@DeleteMapping("/maintenence/maintenenceId/{maintenenceId}")
	public void deleteMaintenence(@PathVariable("maintenenceId") int maintenenceId) {
		maintenenceService.deleteMaintenence(maintenenceId);
	}

	/**
	 * 
	 * @param maintenenceId
	 * @description get a maintenance by its Id
	 */
	@GetMapping("/maintenence/maintenenceId/{maintenenceId}")
	public ResponseEntity<Maintenence> getById(@PathVariable("maintenenceId") int maintenenceId) {
		return ResponseEntity.ok(maintenenceService.getById(maintenenceId));
	}

	/**
	 * 
	 * @param owner
	 * @description gets a list of maintenances a particular owner has
	 */
	@GetMapping("/maintenence/owner/{owner}")
	public ResponseEntity<List<Maintenence>> getByMaintenenceOwner(@PathVariable("owner") String owner) {
		return ResponseEntity.ok(maintenenceService.getMaintenenceByOwner(owner));
	}

	/**
	 * 
	 * @param startdate
	 * @description gets a list of maintenances which are starting on a give date
	 */
	@GetMapping("/maintenence/startdate/{startdate}")
	public ResponseEntity<List<Maintenence>> getByMaintenenceStartDate(@PathVariable("startdate") String startdate) {
		return ResponseEntity.ok(maintenenceService.getMaintenenceByStartDate(LocalDate.parse(startdate)));
	}

	/**
	 * 
	 * @param enddate
	 * @description gets a list of maintenances which are ending on a given date
	 */
	@GetMapping("/maintenence/enddate/{enddate}")
	public ResponseEntity<List<Maintenence>> getByMaintenenceEndDate(@PathVariable("enddate") String enddate) {
		return ResponseEntity.ok(maintenenceService.getMaintenenceByEndDate(LocalDate.parse(enddate)));
	}

	/**
	 * 
	 * @param priority
	 * @description gets a list of maintenances which are having a given priority
	 */
	@GetMapping("/maintenence/priority/{priority}")
	public ResponseEntity<List<Maintenence>> getByMaintenencePriority(@PathVariable("priority") Priority priority) {
		return ResponseEntity.ok(maintenenceService.getMaintenenceByPriority(priority));
	}

	/**
	 * 
	 * @description gets all the maintenances
	 */
	@GetMapping("/maintenence")
	public ResponseEntity<List> viewAllMaintenence() {
		return ResponseEntity.ok(maintenenceService.getAllMaintenences());
	}

	/**
	 * 
	 * @param taskId
	 * @description gets a task by its id
	 */
	@GetMapping("/maintenence/task/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable("taskId") int taskId) {
		return maintenenceService.getTaskById(taskId);
	}

	/**
	 * 
	 * @description gets the list of all tasks
	 */
	@GetMapping("/maintenence/task")
	public ResponseEntity<List> viewAllTasks() {
		return maintenenceService.getAllTasks();
	}

	/**
	 * 
	 * @param maintenenceId
	 * @param taskId
	 * @description assigns a particular task to maintenance by getting the task
	 *              with its id
	 */
	@GetMapping("/maintenence/task/assign/maintenenceId/{maintenenceId}/taskId/{taskId}")
	public ResponseEntity<String> assignTask(@PathVariable("maintenenceId") int maintenenceId,
			@PathVariable("taskId") int taskId) {
		return maintenenceService.assignTask(maintenenceId, taskId);
	}

	/**
	 * 
	 * @param maintenenceId
	 * @param taskId
	 * @description assigns a particular task to maintenance by getting the task
	 *              with its id
	 */
	@GetMapping("/maintenence/task/assigntask/maintenenceId/{maintenenceId}/taskId/{taskId}")
	public ResponseEntity<String> assingTaskToMaintenence(@PathVariable("maintenenceId") int maintenenceId,
			@PathVariable("taskId") int taskId) {
		return ResponseEntity.ok(maintenenceService.assingTasktoMaintenence(maintenenceId, taskId));
	}

	/**
	 * 
	 * @param task
	 * @param maintenenceId
	 * @description assigns task to a maintenance by adding a new task
	 */
	@PostMapping("/maintenence/addtask/maintenenceId/{maintenenceId}")
	public ResponseEntity<String> addTask(@RequestBody Task task, @PathVariable("maintenenceId") int maintenenceId) {
		return ResponseEntity.ok(maintenenceService.addTask(task, maintenenceId));
	}

	/**
	 * 
	 * @param owner
	 * @param priority
	 * @description get all the maintenances of a particular owner
	 */
	@GetMapping("/maintenence/maintenenceOwner/{owner}/priority/{priority}")
	public ResponseEntity<List<Maintenence>> getMaintenenceByOwnerAndPriority(@PathVariable("owner") String owner,
			@PathVariable("priority") Priority priority) {
		return ResponseEntity.ok(maintenenceService.getMaintenenceByOwnerAndPriority(owner, priority));
	}

	/**
	 * 
	 * @param tripName
	 * @description get all the maintenances a particular trip has..
	 */
	@GetMapping("/maintenence/tripName/{tripName}")
	public ResponseEntity<List<Maintenence>> getMainteneceByTrip(@PathVariable("tripName") String tripName) {
		return ResponseEntity.ok(maintenenceService.getMainteneceByTrip(tripName));
	}

	/**
	 * 
	 * @param priority
	 * @param tripName
	 * @description gets all the maintenances in a particular trip having a
	 *              particular priority
	 */
	@GetMapping("/maintenence/priority/{priority}/tripName/{tripName}")
	public ResponseEntity<List<Maintenence>> getMaintenenceByPriorityAndTrip(
			@PathVariable("priority") Priority priority, @PathVariable("tripName") String tripName) {
		return ResponseEntity.ok(maintenenceService.getMaintenenceByPriorityAndTrip(priority, tripName));
	}

	/**
	 * 
	 * @param tripId
	 * @description gets all the maintenances a having a particular tripId
	 */
	@GetMapping("/maintenence/tripId/{tripId}")
	public ResponseEntity<List<Maintenence>> getMainteneceByTripId(@PathVariable("tripId") int tripId) {
		return ResponseEntity.ok(maintenenceService.getMainteneceByTripId(tripId));
	}

	/**
	 * 
	 * @param status
	 * @param maintenenceId
	 * @description updates the status of a maintenance
	 */
	@PatchMapping("/maintenence/status/{status}/maintenenceId/{maintenenceId}")
	public ResponseEntity<Integer> updateMaintenenceStatus(@PathVariable("status") Status status,
			@PathVariable("maintenenceId") int maintenenceId) {
		return ResponseEntity.ok(maintenenceService.updateMaintenenceStatus(status, maintenenceId));
	}
}
