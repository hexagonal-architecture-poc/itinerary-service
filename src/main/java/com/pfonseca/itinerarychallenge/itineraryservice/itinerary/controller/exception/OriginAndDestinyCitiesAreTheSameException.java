package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OriginAndDestinyCitiesAreTheSameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5387990166128619325L;

	public OriginAndDestinyCitiesAreTheSameException() {
		super("Origin and destiny cities are the same");
	}
	
}
