package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.exception.ItineraryNotFoundException;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.filter.ItineraryFilter;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.application.service.ItineraryService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryController.class);
	
	@Autowired
	private ItineraryService itineraryService;
	
	@GetMapping()
	@ApiOperation(value="Listing itineraries based on a filter")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "destiny", value = "Destiny city id", required = false),
	    @ApiImplicitParam(name = "origin", value = "Origin city id", required = false),
	    @ApiImplicitParam(name = "departureAfter", value = "Departure after. (format: 'HH:mm:ss')", required = false)
	})
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	}
	)
	public Page<Itinerary> list(ItineraryFilter filter, Pageable pageable){
		LOGGER.info("Listing itineraries. Filter: {}", filter);
		return itineraryService.list(filter, pageable);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Get itinerary by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved itinerary"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	public Itinerary getById(@PathVariable Long id) {
		LOGGER.info("get itinerary by id. id: {}", id);
		Optional<Itinerary> itinerary = itineraryService.getById(id);
		return itinerary.orElseThrow(ItineraryNotFoundException::new);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Save a new Itinerary")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully created"),
	        @ApiResponse(code = 400, message = "Bad request")
	}
	)
	public Itinerary save(@Valid @RequestBody Itinerary itinerary) {
		LOGGER.info("Saving itinerary");
		return itineraryService.save(itinerary);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Update an Itinerary")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated"),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	public Itinerary update(@Valid @RequestBody Itinerary itinerary, @PathVariable Long id) {
		
		LOGGER.info("Updating itinerary. id: {}", id);
		itinerary.setId(id);
		
		return itineraryService.update(itinerary);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Delete an Itinerary")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully deleted"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	public void delete(@PathVariable Long id) {
		LOGGER.info("Deleting itinerary. id: {}", id);
		itineraryService.delete(id);
	}
	
}
