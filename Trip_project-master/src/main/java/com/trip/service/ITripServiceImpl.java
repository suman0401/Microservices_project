package com.trip.service;
/**
 * @author SumanD
 *
 */
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.trip.exception.TripNotFoundException;
import com.trip.model.Maintenence;
import com.trip.model.Priority;
import com.trip.model.Status;
import com.trip.model.Trip;
import com.trip.repository.ITripRepository;

@Service
public class ITripServiceImpl implements ITripService {
	@Autowired
	ITripRepository tripRepository;
	public final static String BASEURL = "http://localhost:8082/maintenence-service";
	@Autowired
	RestTemplate restTemplate;

	@Override
	public Trip addTrip(Trip trip) {
		return tripRepository.save(trip);
	}

	@Override
	public void updateTrip(Trip trip) {
		tripRepository.save(trip);
	}

	@Override
	public void deleteTrip(int tripId) {
		tripRepository.deleteById(tripId);
	}

	@Override
	public List<Trip> getallTrip() {
		return tripRepository.findAll();
	}

	@Override
	public List<Trip> getByTripName(String tripName) throws TripNotFoundException {
		List<Trip> tripList = tripRepository.findByTripName(tripName);
		if (tripList.isEmpty()) {
			throw new TripNotFoundException("Invalid Trip Name");
		}
		return tripList;
	}

	@Override
	public List<Trip> getByTripOwner(String tripowner) throws TripNotFoundException {
		List<Trip> tripList = tripRepository.findByTripOwner(tripowner);
		if (tripList.isEmpty()) {
			throw new TripNotFoundException("Invalid Trip Owner");
		}
		return tripList;
	}

	@Override
	public List<Trip> getByTripstartDate(LocalDate tripstartdate) throws TripNotFoundException {
		List<Trip> tripList = tripRepository.findByTripStartDate(tripstartdate);
		if (tripList.isEmpty()) {
			throw new TripNotFoundException("Invalid Trip Start Date");
		}
		return tripList;
	}

	@Override
	public Trip getById(int tripId) throws TripNotFoundException {
		Trip trip = tripRepository.findById(tripId).get();
		if (trip == null) {
			throw new TripNotFoundException("Invalid Trip Id");
		}
		return trip;
	}

	@Override
	public List<Trip> getByTripPriority(Priority priority) throws TripNotFoundException {
		List<Trip> tripList = tripRepository.findByTripPriority(priority);
		if (tripList.isEmpty()) {
			throw new TripNotFoundException("Invalid Trip Priority");
		}
		return tripList;
	}

	@Override
	public List<Trip> getByTripStatus(Status status) throws TripNotFoundException {
		List<Trip> tripList = tripRepository.findByTripStatus(status);
		if (tripList.isEmpty()) {
			throw new TripNotFoundException("Invalid Trip Status");
		}
		return tripList;
	}

	// ***************************************************************************************************************//

	@Override
	public Maintenence createMaintenence(Maintenence maintenence, int tripId) {
		System.out.println(maintenence);
		String url = BASEURL + "/addmaintenence/" + tripId;
		Trip gettingtrip = getById(tripId);
		maintenence.setTrip(gettingtrip);
		ResponseEntity<Maintenence> response = restTemplate.postForEntity(url, maintenence, Maintenence.class);
		return response.getBody();
//		HttpHeaders headers= new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		
//		HttpEntity<Maintenence> requestEntity = new HttpEntity<>(maintenence, headers);
//        ResponseEntity<Maintenence> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                requestEntity,
//                Maintenence.class
//        );
// return requestEntity.getBody();

	}

	@Override
	public List<Maintenence> readByTripNameAndMaintenanceMaintenenceName(String tripname, String maintenencename) {
		String url = BASEURL + "/tripname/" + tripname + "/maintenencename/" + maintenencename;
		ResponseEntity<List> maintenencelist = restTemplate.getForEntity(url, List.class);
		return maintenencelist.getBody();
	}

	@Override
	public void updateMaintenence(Maintenence maintenence, int maintenenceId) {
		String url = BASEURL + "/updateMaintenence/maintenenceId/" + maintenenceId;
		restTemplate.put(url, maintenence, maintenenceId);

	}

	@Override
	public void deleteMaintenence(int maintenenceId) {
		String url = BASEURL + "/deleteMaintenence/" + maintenenceId;
		restTemplate.delete(url, maintenenceId);

	}

	@Override
	public List<Maintenence> readByTripOwner(String tripowner) {
		String url = BASEURL + "/maintenence/tripOwner/" + tripowner;
		ResponseEntity<List> maintenencelist = restTemplate.getForEntity(url, List.class);
		return maintenencelist.getBody();
	}

	@Override
	public List<Maintenence> readByTripstartDate(LocalDate startdate) {
		String url = BASEURL + "/maintenence/tripstartdate/" + startdate;
		ResponseEntity<List> maintenencelist = restTemplate.getForEntity(url, List.class);
		return maintenencelist.getBody();
	}

	@Override
	public List<Maintenence> readByTripPriority(Priority priority) {
		String url = BASEURL + "/maintenence/trippriority/" + priority;
		ResponseEntity<List> maintenencelist = restTemplate.getForEntity(url, List.class);
		return maintenencelist.getBody();
	}

	@Override
	public List<Maintenence> readByTripPriorityAndTripName(Priority priority, String tripname) {
		String url = BASEURL + "/maintenence/trippriority/" + priority + "/tripname/" + tripname;
		ResponseEntity<List> maintenencelist = restTemplate.getForEntity(url, List.class);
		return maintenencelist.getBody();
	}

	@Override
	public List<Maintenence> readByTripOwnerAndTripStatus(String tripowner, Status status) {
		String url = BASEURL + "/maintenence/tripowner/" + tripowner + "/tripstatus/" + status;
		ResponseEntity<List> maintenencelist = restTemplate.getForEntity(url, List.class);
		return maintenencelist.getBody();
	}

	@Override
	public List<Maintenence> readByTripId(int tripId) {
		String url=BASEURL+"/maintenence/tripId/"+tripId;
		ResponseEntity<List> maintenencelist=restTemplate.getForEntity(url, List.class);
		return maintenencelist.getBody();
	}

}
