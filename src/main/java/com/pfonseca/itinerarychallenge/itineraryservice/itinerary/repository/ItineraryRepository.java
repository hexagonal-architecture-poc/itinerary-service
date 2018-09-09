package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long>, JpaSpecificationExecutor<Itinerary>{

	Optional<Itinerary> findById(Long id);
	
}
