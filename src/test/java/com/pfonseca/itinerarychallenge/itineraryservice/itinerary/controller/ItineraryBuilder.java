package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller;

import java.time.LocalTime;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;

public class ItineraryBuilder {

	public static final LocalTime ARRIVAL_TIME = LocalTime.of(11, 11);
	public static final LocalTime DEPARTURE_TIME = LocalTime.of(10, 11);
	public static final City ORIGIN = new City("Zaragoza");
	public static final City DESTINY = new City("Barcelona");
	
	
	private Long id;
	private City origin;
	private City destiny;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	
	
	public ItineraryBuilder withDefaultValues() {
		return withOrigin(ORIGIN)
				.withDestiny(DESTINY)
				.withDepartureTime(DEPARTURE_TIME)
				.withArrivalTime(ARRIVAL_TIME);
	}

	public ItineraryBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	public ItineraryBuilder withOrigin(City origin) {
		this.origin = origin;
		return this;
	}
	
	public ItineraryBuilder withDestiny(City destiny) {
		this.destiny = destiny;
		return this;
	}
	
	public ItineraryBuilder withDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
		return this;
	}
	
	public ItineraryBuilder withArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
		return this;
	}
	

	public Itinerary build() {
		
		Itinerary itinerary = new Itinerary();
		
		itinerary.setArrivalTime(arrivalTime);
		itinerary.setDepartureTime(departureTime);
		itinerary.setDestiny(destiny);
		itinerary.setOrigin(origin);
		itinerary.setId(id);
		
		return itinerary;
	}
	
}
