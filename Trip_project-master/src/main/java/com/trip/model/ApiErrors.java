package com.trip.model;
/**
 * @author SumanD
 *
 */
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ApiErrors {
	LocalDateTime timestamp;
	String message;
	int status;
	String error;
}
