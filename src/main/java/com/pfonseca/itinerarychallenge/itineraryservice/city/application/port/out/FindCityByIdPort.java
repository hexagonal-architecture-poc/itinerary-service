package com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.out;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;

import java.util.Optional;

public interface FindCityByIdPort {
    Optional<City> findById(Long id);
}
