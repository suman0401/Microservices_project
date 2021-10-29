package com.resources.repository;
/**
 * @author SumanD
 *
 */
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.resources.model.Available;
import com.resources.model.Workers;
@Repository
public interface IWorkersRepository extends JpaRepository<Workers, Integer> {
	
//derived queries
	List<Workers> findByWorkersName(String workersName);
	List<Workers> findByWorkersType(String workersType);
	List<Workers> findByWorkersavailablefrom(LocalDate availableFrom);
	List<Workers> findByAvailability(Available availability);
	
	@Transactional
	@Modifying
	@Query(value="update workers set task_id=?1 where workers_id=?2",nativeQuery = true)
	void assignWorkersToTask(int taskId, int workersId);
	
	@Transactional
	@Modifying
	@Query(value="update workers set task_id=NULL where workers_id=?1",nativeQuery = true)
	void removeWorkersToTask(int taskId);
	

}
