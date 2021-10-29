package com.taskapp.service;

/**
 * @author SathyaPriyanaka
 *
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.taskapp.exception.IdNotFoundException;
import com.taskapp.exception.TaskNotFoundException;
import com.taskapp.model.Priority;
import com.taskapp.model.Status;
import com.taskapp.model.Task;
import com.taskapp.model.Workers;

public interface ITaskService {

	Task addTask(Task task);

	void updateTask(Task task);

	void deleteTask(int taskId);

	List<Task> getAll();

	Task getById(int taskId) throws IdNotFoundException;

	List<Task> getByTaskName(String taskName) throws TaskNotFoundException;

	List<Task> getByTaskOwner(String taskOwner) throws TaskNotFoundException;

	List<Task> getByTaskDuration(String taskDuration) throws TaskNotFoundException;

	List<Task> getByTaskPriority(Priority taskPriority);

	List<Task> getByTaskEndDate(LocalDate endDate);

	List<Task> getByTaskStartDate(LocalDate startDate);

	List<Task> getByTaskStatus(Status status);

	List<Task> getByTaskNameAndworkersType(String taskName, String workersType);

	List<Task> findByTaskNameAndWorkersAvailability(String taskName, String availability);

	ResponseEntity<String> removeWorkersToTask(int taskId);

	ResponseEntity<String> assignWorkers(int workersId, int taskId);

	public ResponseEntity<Workers> getWorkersById(int workerId);

	public ResponseEntity<Workers> getWorkersByType(String workersType);

	public ResponseEntity<Workers> getWorkersByName(String workersName);

	public List<Workers> getAllWorkers();

	public List<Workers> getByWorkersAvailableFrom(LocalDate workersavailablefrom);

}
