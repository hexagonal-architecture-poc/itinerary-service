package com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.out;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllCitiesPort {
    Page<City> findAll(Pageable pageable);
}
