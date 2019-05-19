package com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.in;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityUseCase {
    boolean exists(Long id);
    Optional<City> getById(Long id);
    Page<City> list(Pageable pageable);
}
