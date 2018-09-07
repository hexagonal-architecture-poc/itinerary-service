package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.filter.ItineraryFilter;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.service.ItineraryService;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {

	@Autowired
	private ItineraryService itineraryService;
	
	@GetMapping()
	public Page<Itinerary> list(ItineraryFilter filter, Pageable pageable){
		return itineraryService.list(filter, pageable);
	}
	
}
