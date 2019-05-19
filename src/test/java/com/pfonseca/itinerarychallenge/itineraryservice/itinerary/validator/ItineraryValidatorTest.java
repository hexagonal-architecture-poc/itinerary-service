package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.validator;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import com.pfonseca.itinerarychallenge.itineraryservice.city.application.service.CityService;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.DepartureTimeAfterArrivalTimeException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.DestinyCityNotFoundException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.OriginAndDestinyCitiesAreTheSameException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.OriginCityNotFoundException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;

@RunWith(MockitoJUnitRunner.class)
public class ItineraryValidatorTest {

	@InjectMocks
	private ItineraryValidator itineraryValidator;
	
	@Mock
	private CityService cityService;
	
	
	private Itinerary validItinerary;
	
	@Before
	public void setup() {
		
		this.validItinerary = new Itinerary();
		this.validItinerary.setDepartureTime(LocalTime.of(10,  11));
		this.validItinerary.setArrivalTime(LocalTime.of(12,  11));
		this.validItinerary.setOrigin(new City(1L, "City1"));
		this.validItinerary.setDestiny(new City(2L, "City2"));
		
		Mockito.when(this.cityService.exists(Mockito.anyLong())).thenReturn(true);
	}
	
	@Test
	public void givenItineraries_whenAValidItinerary_thenNotThrowException() {
		try {
			this.itineraryValidator.validate(validItinerary);
		} catch(Exception e ) {
			Assert.fail();
		}
	}
	
	@Test(expected=DepartureTimeAfterArrivalTimeException.class)
	public void givenItineraries_whenDepartureTimeIsAfterArrivalTime_thenNotThrowException() {
		
		validItinerary.setDepartureTime(LocalTime.MAX);
		
		this.itineraryValidator.validate(validItinerary);
	}
	
	@Test(expected=OriginCityNotFoundException.class)
	public void givenItineraries_whenOriginCityIsNotFound_thenNotThrowException() {
		Mockito.when(this.cityService.exists(Mockito.eq(1L))).thenReturn(false);
		this.itineraryValidator.validate(validItinerary);
	}
	
	@Test(expected=DestinyCityNotFoundException.class)
	public void givenItineraries_whenDestinyCityIsNotFound_thenNotThrowException() {
		Mockito.when(this.cityService.exists(Mockito.eq(2L))).thenReturn(false);
		this.itineraryValidator.validate(validItinerary);
	}
	
	@Test(expected=OriginAndDestinyCitiesAreTheSameException.class)
	public void givenItineraries_whenOriginAndDestinyCitiesAreTheSame_thenNotThrowException() {
		
		this.validItinerary.setDestiny(this.validItinerary.getOrigin());
		
		this.itineraryValidator.validate(validItinerary);
	}
	
}
