package com.mainteneceapp.repo;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mainteneceapp.model.Maintenence;
import com.mainteneceapp.model.Priority;
import com.mainteneceapp.model.Status;

@Repository
/**
 * 
 * @author ManikantaJV
 *
 */
public interface IMaintenenceRepo extends JpaRepository<Maintenence, Integer> {

	public Maintenence findByMaintenenceName(String name);
	
	public List<Maintenence> findByMaintenenceOwner(String owner); 
	
	public List<Maintenence> findByMaintenenceStartDate(LocalDate startDate);  
	
	public List<Maintenence> findByMaintenenceEndDate(LocalDate endDate);
	
	public List<Maintenence> findByMaintenencePriority(Priority priority);
	
	@Query("from Maintenence m where m.maintenenceOwner=?1 and m.maintenencePriority=?2")
	public List<Maintenence> findMaintenenceByOwnerAndPriority(String owner, Priority priority);
	
	@Query("from Maintenence m inner join m.trip t where t.tripName=?1")
	public List<Maintenence> findMainteneceByTrip(String tripName);
	
	@Query("from Maintenence m inner join m.trip t where m.maintenencePriority=?1 and t.tripName=?2")
	public List<Maintenence> findMaintenenceByPriorityAndTrip(Priority priority, String tripName);
	
	@Query("from Maintenence m inner join m.trip t where t.tripId=?1")
	public List<Maintenence> findMainteneceByTripId(int tripId);
	
	@Transactional
	@Modifying
	@Query("update Maintenence m set m.maintenenceStatus=?1 where m.maintenenceId=?2 " )
	public int updateMaintenenceStatus(Status status, int maintenenceId);
}
