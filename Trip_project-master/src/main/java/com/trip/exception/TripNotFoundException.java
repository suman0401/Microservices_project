package com.trip.exception;
/**
 * @author SumanD
 *
 */
public class TripNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public TripNotFoundException() {
		super();
	}

	public TripNotFoundException(String message) {
		super(message);
	}
	

}
