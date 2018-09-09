package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OriginCityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6155219798869431437L;

	public OriginCityNotFoundException() {
		super("Origin city not found");
	}
	
}
