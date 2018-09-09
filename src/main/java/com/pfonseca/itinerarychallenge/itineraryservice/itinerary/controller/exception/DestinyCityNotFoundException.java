package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DestinyCityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7522688144743664063L;

	public DestinyCityNotFoundException() {
		super("Destiny city not found");
	}
	
}
