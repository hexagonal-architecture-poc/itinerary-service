package com.pfonseca.itinerarychallenge.itineraryservice.city.adapter.persistence;

import com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.out.ExistsCityPort;
import com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.out.FindAllCitiesPort;
import com.pfonseca.itinerarychallenge.itineraryservice.city.application.port.out.FindCityByIdPort;
import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityPersistence implements ExistsCityPort, FindAllCitiesPort, FindCityByIdPort {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public boolean existsById(Long id) {
        return cityRepository.existsById(id);
    }

    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }
}
