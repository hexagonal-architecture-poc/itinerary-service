package com.pfonseca.itinerarychallenge.itineraryservice.city.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import com.pfonseca.itinerarychallenge.itineraryservice.city.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public boolean exists(Long id) {
		return cityRepository.existsById(id);
	}

	public Page<City> list(Pageable pageable) {
		return cityRepository.findAll(pageable);
	}

	public Optional<City> getById(Long id) {
		return cityRepository.findById(id);
	}

}
