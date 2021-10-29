package com.mainteneceapp.model;

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
/**
 * 
 * @author ManikantaJV
 *
 */
public class ApiErrors {
	LocalDateTime timestamp;
	String message;
	int status;
	String error;
}
