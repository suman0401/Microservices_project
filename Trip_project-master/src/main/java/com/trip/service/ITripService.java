package com.trip.service;
/**
 * @author SumanD
 *
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.http.ResponseEntity;

import com.trip.exception.TripNotFoundException;
import com.trip.model.Maintenence;
import com.trip.model.Priority;
import com.trip.model.Status;
import com.trip.model.Trip;

public interface ITripService {
// CRUD OPERATIONS	
	Trip addTrip(Trip trip);

	void updateTrip(Trip trip);

	void deleteTrip(int tripId);

//ALL LIST AND ID
	List<Trip> getallTrip();

	Trip getById(int tripId) throws TripNotFoundException;

//TRIP LIST WITH METHODS
	List<Trip> getByTripName(String tripName) throws TripNotFoundException;

	List<Trip> getByTripOwner(String tripowner) throws TripNotFoundException;

	List<Trip> getByTripstartDate(LocalDate tripstartdate) throws TripNotFoundException;

	List<Trip> getByTripPriority(Priority priority) throws TripNotFoundException;

	List<Trip> getByTripStatus(Status status) throws TripNotFoundException;

//FOR MAINTENENCE
//MAINTENENCE CRUD OPERATION

	public Maintenence createMaintenence(Maintenence maintenence, int tripId);

	public void updateMaintenence(Maintenence maintenence, int maintenenceId);

	public void deleteMaintenence(int maintenenceId);

//MAINTENENCE METHOD

	List<Maintenence> readByTripNameAndMaintenanceMaintenenceName(String tripname, String maintenencename);

	List<Maintenence> readByTripOwner(String tripowner);

	List<Maintenence> readByTripstartDate(LocalDate startdate);

	List<Maintenence> readByTripPriority(Priority priority);

	List<Maintenence> readByTripPriorityAndTripName(Priority priority,String tripname);

	List<Maintenence> readByTripOwnerAndTripStatus(String tripowner,Status status);
	
	List<Maintenence> readByTripId(int tripId);

}
