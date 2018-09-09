package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartureTimeAfterArrivalTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5844072089004025505L;

	public DepartureTimeAfterArrivalTimeException() {
		super("Departure time is after arrival time");
	}
	
}
