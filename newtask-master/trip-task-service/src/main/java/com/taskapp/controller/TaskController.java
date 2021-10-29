package com.taskapp.controller;
/**
 * @author SathyaPriyanaka
 *
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.taskapp.exception.TaskNotFoundException;
import com.taskapp.model.Priority;
import com.taskapp.model.Status;
import com.taskapp.model.Task;
import com.taskapp.model.Workers;
import com.taskapp.service.ITaskService;

@RestController
@RequestMapping("/task-service")
public class TaskController {
	@Autowired
	ITaskService taskService;

	@GetMapping("/task/{taskId}")
	public ResponseEntity<Task> getById(@PathVariable("taskId") int taskId) {
		Task getTask = taskService.getById(taskId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get a task by id  api");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(getTask);

	}

	@PostMapping("/task")
	public ResponseEntity<Task> addTask(@RequestBody Task task) {
		Task newTask = taskService.addTask(task);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "add a task  api");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(newTask);

	}

	@PutMapping("/task")
	public ResponseEntity<Void> updateTask(@RequestBody Task task) {
		taskService.updateTask(task);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "update a task  api");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).build();

	}

	@DeleteMapping("/task/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable("taskId") int taskId) {
		taskService.deleteTask(taskId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "delete a task  api");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).build();

	}

	@GetMapping("/task/taskname/{taskName}")
	public ResponseEntity<List<Task>> getByName(@PathVariable("taskName") String taskName) {
		List<Task> tasksByName = taskService.getByTaskName(taskName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get all task by name  api");
		return ResponseEntity.ok().headers(headers).body(tasksByName);

	}

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getByAll() {
		List<Task> tasksByName = taskService.getAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get all task by name  api");
		return ResponseEntity.ok().headers(headers).body(tasksByName);

	}

	@GetMapping("/task/taskowner/{taskOwner}")
	public ResponseEntity<List<Task>> getByOwner(@PathVariable("taskOwner") String taskOwner) {
		List<Task> tasksByOwner = taskService.getByTaskOwner(taskOwner);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get all task by owner  api");
		return ResponseEntity.ok().headers(headers).body(tasksByOwner);

	}

	@GetMapping("/task/taskpriority/{taskPriority}")
	public ResponseEntity<List<Task>> getByTaskPriority(@PathVariable("taskPriority") String taskPriority) {
		Priority priorityOfTask = Priority.valueOf(taskPriority.toUpperCase());
		List<Task> taskByPriority = taskService.getByTaskPriority(priorityOfTask);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get all task by priority  api");
		return ResponseEntity.ok().headers(headers).body(taskByPriority);

	}

	@GetMapping("/task/taskstatus/{taskStatus}")
	public ResponseEntity<List<Task>> getByTaskStatus(@PathVariable("taskStatus") String taskStatus) {
		Status statusOfTask = Status.valueOf(taskStatus.toUpperCase());

		List<Task> taskByStatus = taskService.getByTaskStatus(statusOfTask);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get all task by status  api");
		return ResponseEntity.ok().headers(headers).body(taskByStatus);

	}

	@GetMapping("/task/taskStartdate/{taskStartDate}")
	public ResponseEntity<List<Task>> getByTaskStartDate(@PathVariable("taskStartDate") String taskStartDate) {
		List<Task> tasksByTaskStartDate = taskService.getByTaskStartDate(LocalDate.parse(taskStartDate));
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get all task by TaskStartDate  api");
		return ResponseEntity.ok().headers(headers).body(tasksByTaskStartDate);

	}

	@GetMapping("/task/taskduration/{taskDuration}")
	public ResponseEntity<List<Task>> getByTaskDuration(@PathVariable("taskDuration") String taskDuration) {
		List<Task> tasksByDuration = taskService.getByTaskDuration(taskDuration);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get all task by taskDuration  api");
		return ResponseEntity.ok().headers(headers).body(tasksByDuration);

	}

	@GetMapping("/workersId/{workersId}")
	public ResponseEntity<Workers> getWorkersById(@PathVariable("workersId") int workersId) {
		return taskService.getWorkersById(workersId);

	}

	// http://localhost:8083/task-service/workers/workersAll
	@GetMapping("/workers/workersAll")
	public ResponseEntity<List<Workers>> getAllWorkers() {
		List<Workers> workersList = taskService.getAllWorkers();
		return ResponseEntity.status(HttpStatus.OK).body(workersList);
	}

	@GetMapping("/workerstype/{workerstype}")
	public ResponseEntity<Workers> getWorkersByType(@PathVariable("workerstype") String workersType) {
		return taskService.getWorkersByType(workersType);

	}

	// http://localhost:8083/task-service/workers/workersname/suman
	@GetMapping("/workersname/{workersname}")
	public ResponseEntity<Workers> getWorkersByName(@PathVariable("workersname") String workersName) {
		return taskService.getWorkersByName(workersName);

	}

	@GetMapping("/workersavailablefrom/{availablefrom}")
	public List<Workers> getByWorkersByAvailableFrom(@PathVariable("availablefrom") String workersavailablefrom) {
		return taskService.getByWorkersAvailableFrom(LocalDate.parse(workersavailablefrom));

	}

	@GetMapping("/assign/workersId/{workersId}/taskId/{taskId}")
	public ResponseEntity<String> assignWorkersToTask(@PathVariable("workersId") int workersId,
			@PathVariable("taskId") int taskId) {
		return taskService.assignWorkers(workersId, taskId);
	}

	@GetMapping("/remove/taskId/{taskId}")
	public ResponseEntity<String> removeWorkersToTask(@PathVariable("taskId") int taskId) {
		return taskService.removeWorkersToTask( taskId);
	}
	
	@GetMapping("/assignworkers/workersId/{workersId}/taskId/{taskId}")
	public ResponseEntity<ResponseEntity<String>> assignWorkers(@PathVariable("workersId") int workersId,
			@PathVariable("taskId") int taskId) {
		return ResponseEntity.ok(taskService.assignWorkers(workersId, taskId));
	}
	

}
