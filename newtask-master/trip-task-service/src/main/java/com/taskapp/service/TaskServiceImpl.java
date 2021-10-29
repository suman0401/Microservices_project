package com.taskapp.service;

/**
 * @author SathyaPriyanaka
 *
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.taskapp.exception.IdNotFoundException;
import com.taskapp.exception.TaskNotFoundException;
import com.taskapp.model.Priority;
import com.taskapp.model.Status;
import com.taskapp.model.Task;
import com.taskapp.model.Workers;
import com.taskapp.repository.ITaskRepository;

@Service
@Transactional
public class TaskServiceImpl implements ITaskService {
	@Autowired
	ITaskRepository taskRepository;
	@Autowired
	private RestTemplate restTemplate;

	public static final String BASEURL = "http://localhost:8081/workers-service/workers/";

	@Override
	public ResponseEntity<Workers> getWorkersById(int workersId) {
		String url = BASEURL + "workersId/" + workersId;
		return restTemplate.getForEntity(url, Workers.class);
	}

	@Override
	public ResponseEntity<Workers> getWorkersByType(String workersType) {
		String url = BASEURL + "/workerstype/" + workersType;
		return restTemplate.getForEntity(url, Workers.class);
	}

	@Override
	public ResponseEntity<Workers> getWorkersByName(String workersName) {
		String url = BASEURL + "/workersname/" + workersName;
		return restTemplate.getForEntity(url, Workers.class);
	}

	@Override
	public List<Workers> getAllWorkers() {
		String url = BASEURL;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		HttpHeaders headers = response.getHeaders();
		List<String> header = headers.get("desc");
		System.out.println(response.getStatusCodeValue() + "    " + header);
		return response.getBody();
	}

	@Override
	public List<Workers> getByWorkersAvailableFrom(LocalDate availableFrom) {
		String url = BASEURL + "/workersavailablefrom/" + availableFrom;
		return restTemplate.getForObject(url, List.class);
	}

	@Override
	public ResponseEntity<String> removeWorkersToTask(int taskId) {

		Task task = taskRepository.findById(taskId).get();
		task.setResourcesList(null);
		taskRepository.save(task);
		return ResponseEntity.ok("removed");
	}

	@Override
	public ResponseEntity<String> assignWorkers(int workersId, int taskId) {
		String url = BASEURL + "workersId/" + workersId;
		ResponseEntity<Workers> rworker = restTemplate.getForEntity(url, Workers.class);
		Workers worker = rworker.getBody();
		Task task = taskRepository.findById(taskId).get();
		task.setResourcesList(new HashSet<>(Arrays.asList(worker)));
		taskRepository.save(task);
		return ResponseEntity.ok("assign workers to task");

	}

	@Override
	public Task addTask(Task task) {

		return taskRepository.save(task);
	}

	@Override
	public void updateTask(Task task) {

		taskRepository.save(task);
	}

	@Override
	public void deleteTask(int taskId) {
		taskRepository.deleteById(taskId);

	}

	@Override
	public List<Task> getAll() {

		return taskRepository.findAll();
	}

	@Override
	public List<Task> getByTaskName(String taskName) throws TaskNotFoundException {
		List<Task> taskList = taskRepository.findByTaskName(taskName);
		if (taskList.isEmpty())
			throw new TaskNotFoundException("Task Not Found");

		return taskList;
	}

	@Override
	public List<Task> getByTaskOwner(String taskOwner) throws TaskNotFoundException {
		List<Task> taskList = taskRepository.findByTaskOwner(taskOwner);
		if (taskList.isEmpty())
			throw new TaskNotFoundException("Task Not Found");

		return taskList;
	}

	@Override
	public List<Task> getByTaskDuration(String taskDuration) throws TaskNotFoundException {
		List<Task> taskList = taskRepository.findByTaskDuration(taskDuration);
		if (taskList.isEmpty())
			throw new TaskNotFoundException("Task Not Found");

		return taskList;
	}

	@Override
	public List<Task> getByTaskPriority(Priority taskPriority) {

		return taskRepository.findByTaskPriority(taskPriority);
	}

	@Override
	public List<Task> getByTaskEndDate(LocalDate endDate) {

		return taskRepository.findByTaskEndDate(endDate);
	}

	@Override
	public List<Task> getByTaskStartDate(LocalDate startDate) {

		return taskRepository.findByTaskStartDate(startDate);
	}

	@Override
	public List<Task> getByTaskStatus(Status status) {

		return taskRepository.findByTaskStatus(status);
	}

	@Override
	public Task getById(int taskId) throws IdNotFoundException {

		return taskRepository.findById(taskId).orElseThrow(() -> new IdNotFoundException("Id not found"));

	}

	@Override
	public List<Task> getByTaskNameAndworkersType(String taskName, String workersType) {

		return taskRepository.findByTaskNameAndworkersType(taskName, workersType);
	}

	@Override
	public List<Task> findByTaskNameAndWorkersAvailability(String taskName, String availability) {

		return taskRepository.findByTaskNameAndWorkersAvailability(taskName, availability);
	}

}
