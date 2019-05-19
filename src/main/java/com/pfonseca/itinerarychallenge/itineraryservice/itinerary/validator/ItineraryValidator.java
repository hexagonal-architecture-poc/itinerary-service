package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.validator;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import com.pfonseca.itinerarychallenge.itineraryservice.city.application.service.CityService;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.DepartureTimeAfterArrivalTimeException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.DestinyCityNotFoundException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.OriginAndDestinyCitiesAreTheSameException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.OriginCityNotFoundException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;

@Service
public class ItineraryValidator {

	@Autowired
	private CityService cityService;
	
	public void validate(Itinerary itinerary) {
		
		validateTime(itinerary);
		
		validateCities(itinerary);
		
	}

	private void validateCities(Itinerary itinerary) {
		
		City origin = itinerary.getOrigin();
		if(! cityService.exists(origin.getId())){
			throw new OriginCityNotFoundException();
		}

		City destiny = itinerary.getDestiny();
		if(! cityService.exists(destiny.getId())){
			throw new DestinyCityNotFoundException();
		}
		
		if(destiny.getId().equals(origin.getId())) {
			throw new OriginAndDestinyCitiesAreTheSameException();
		}
	}

	private void validateTime(Itinerary itinerary) {
		
		LocalTime departureTime = itinerary.getDepartureTime();
		LocalTime arrivalTime = itinerary.getArrivalTime();
		
		if(departureTime.isAfter(arrivalTime)) {
			throw new DepartureTimeAfterArrivalTimeException();
		}
	}

}
