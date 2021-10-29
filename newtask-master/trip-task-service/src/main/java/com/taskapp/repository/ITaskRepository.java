package com.taskapp.repository;

/**
 * @author SathyaPriyanaka
 *
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskapp.model.Priority;
import com.taskapp.model.Status;
import com.taskapp.model.Task;
import com.taskapp.model.Workers;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {
	List<Task> findByTaskName(String taskName);

	List<Task> findByTaskOwner(String taskOwner);

	List<Task> findByTaskDuration(String taskDuration);

	List<Task> findByTaskPriority(Priority taskPriority);

	List<Task> findByTaskEndDate(LocalDate endDate);

	List<Task> findByTaskStartDate(LocalDate startDate);

	List<Task> findByTaskStatus(Status status);

	@Query("from Task t inner join t.resourcesList w where t.taskStartDate=?1 and  w.workersavailablefrom=?2")
	List<Task> findByTaskStartDateWorkerssAvailablefrom(LocalDate startDate, LocalDate workersavailablefrom);

	@Query("from Task t inner join t.resourcesList w where t.taskName=?1 and  w.workersType=?2")
	List<Task> findByTaskNameAndworkersType(String taskName, String workersType);

	@Query("from Task t inner join t.resourcesList w where t.taskName=?1 and  w.availability=?2")
	List<Task> findByTaskNameAndWorkersAvailability(String taskName, String availability);

}
