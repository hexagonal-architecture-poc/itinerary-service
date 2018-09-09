package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.ItineraryNotFoundException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.filter.ItineraryFilter;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.repository.ItineraryRepository;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.validator.ItineraryValidator;

@Service
public class ItineraryService {

	@Autowired
	private ItineraryRepository itineraryRepository;
	
	@Autowired
	private ItineraryValidator itineraryValidator;
	
	public Page<Itinerary> list(ItineraryFilter filter, Pageable pageable) {
		return itineraryRepository.findAll(filter.buildQuery(), pageable);
	}

	public Optional<Itinerary> getById(Long id) {
		return itineraryRepository.findById(id);
	}
	
	@Transactional
	public Itinerary save(Itinerary itinerary) {
		
		this.itineraryValidator.validate(itinerary);
		
		return itineraryRepository.save(itinerary);
	}

	@Transactional
	public void delete(Long id) {
		Itinerary itinerary = this.getById(id).orElseThrow(ItineraryNotFoundException::new);
		itineraryRepository.delete(itinerary);
	}

	@Transactional
	public Itinerary update(@Valid Itinerary itinerary) {
		
		if(!itineraryRepository.existsById(itinerary.getId())) {
			throw new ItineraryNotFoundException();
		}
		
		return save(itinerary);
	}

}
