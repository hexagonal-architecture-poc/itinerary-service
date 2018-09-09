package com.pfonseca.itinerarychallenge.itineraryservice.city.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City>{

	Optional<City> findById(Long id);
	
}
