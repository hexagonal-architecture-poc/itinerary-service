package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.filter.ItineraryFilter;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.repository.ItineraryRepository;

@Service
public class ItineraryService {

	@Autowired
	private ItineraryRepository itineraryRepository;
	
	public Page<Itinerary> list(ItineraryFilter filter, Pageable pageable) {
		return itineraryRepository.findAll(filter.buildQuery(), pageable);
	}

}
