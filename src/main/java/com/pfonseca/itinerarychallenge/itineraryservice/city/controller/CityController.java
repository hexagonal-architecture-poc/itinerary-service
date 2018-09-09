package com.pfonseca.itinerarychallenge.itineraryservice.city.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfonseca.itinerarychallenge.itineraryservice.city.controller.exception.CityNotFoundException;
import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import com.pfonseca.itinerarychallenge.itineraryservice.city.service.CityService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cities")
public class CityController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private CityService cityService;
	
	@GetMapping
	@ApiOperation(value="Listing cities")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	}
	)
	public Page<City> list(Pageable pageable){
		LOGGER.info("Listing cities.");
		return cityService.list(pageable);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Get City by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved city"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	public City getById(@PathVariable Long id) {
		LOGGER.info("get city by id. id: {}", id);
		Optional<City> city = cityService.getById(id);
		return city.orElseThrow(CityNotFoundException::new);
	}
	
}
