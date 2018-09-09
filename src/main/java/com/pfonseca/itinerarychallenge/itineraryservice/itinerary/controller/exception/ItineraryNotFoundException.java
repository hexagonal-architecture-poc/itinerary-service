package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItineraryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7129378149974101328L;

	public ItineraryNotFoundException() {
		super("Itinerary not found");
	}

}
