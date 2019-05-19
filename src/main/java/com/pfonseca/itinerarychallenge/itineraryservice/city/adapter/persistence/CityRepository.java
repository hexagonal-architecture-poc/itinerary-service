package com.pfonseca.itinerarychallenge.itineraryservice.city.adapter.persistence;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City>{
	
}
