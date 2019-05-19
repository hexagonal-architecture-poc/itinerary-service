package com.pfonseca.itinerarychallenge.itineraryservice.city.application.service;

import java.util.Optional;

import com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.in.CityUseCase;
import com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.out.ExistsCityPort;
import com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.out.FindAllCitiesPort;
import com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.out.FindCityByIdPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;

@Service
public class CityService implements CityUseCase {

	@Autowired
	private ExistsCityPort existsCityPort;

	@Autowired
	private FindAllCitiesPort findAllCitiesPort;

	@Autowired
	private FindCityByIdPort findCityByIdPort;
	
	public boolean exists(Long id) {
		return existsCityPort.existsById(id);
	}

	public Page<City> list(Pageable pageable) {
		return findAllCitiesPort.findAll(pageable);
	}

	public Optional<City> getById(Long id) {
		return findCityByIdPort.findById(id);
	}

}
