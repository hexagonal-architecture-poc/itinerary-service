package com.pfonseca.itinerarychallenge.itineraryservice.city.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8096771519644980907L;

	public CityNotFoundException() {
		super("City not found");
	}
	
}
