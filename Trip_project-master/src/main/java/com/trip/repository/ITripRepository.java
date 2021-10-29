package com.trip.repository;
/**
 * @author SumanD
 *
 */
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trip.model.Maintenence;
import com.trip.model.Priority;
import com.trip.model.Status;
import com.trip.model.Trip;
@Repository
public interface ITripRepository extends JpaRepository<Trip, Integer> {
//derived queries	
	List<Trip> findByTripName(String tripName);
	List<Trip> findByTripOwner(String tripType);
	List<Trip> findByTripStartDate(LocalDate tripstartdate);
	List<Trip> findByTripEndDate(LocalDate tripenddate);
	List<Trip> findByTripPriority(Priority priority);
	List<Trip> findByTripStatus(Status status);
	
//   @Query(value="select * from maintenence m inner join trip t on m.maintenence_id=t.trip_id where t.tripName=?1 and m.maintenenceName=?2",nativeQuery = true)
//	List<Maintenence> getByTripNameAndMaintenanceMaintenenceName(String tripname,String maintenencename);
//	List<Maintenence> getByTripNameAndMaintenanceMaintenenceOwner(String tripname,String maintenencename);
//	List<Maintenence> getByTripNameAndMaintenanceMaintenenceStartDate(String tripname,LocalDate maintanancestartdate);
//	List<Maintenence> getByTripNameAndMaintenanceMaintenencePriority(String tripname,String maintenencename);
}
