package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.filter.ItineraryFilter;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.service.ItineraryService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryController.class);
	
	@Autowired
	private ItineraryService itineraryService;
	
	@GetMapping()
	@ApiOperation(value="Listing itineraries based on a filter")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "destiny", value = "Destiny city id", required = true, dataType = "long", paramType = "destiny"),
	    @ApiImplicitParam(name = "origin", value = "Origin city id", required = true, dataType = "long", paramType = "origin"),
	    @ApiImplicitParam(name = "departureAfter", value = "Departure after. (format: 'HH:mm:ss')", required = true, dataType = "LocalTime", paramType = "departureAfter")
	})
	public Page<Itinerary> list(ItineraryFilter filter, Pageable pageable){
		LOGGER.info("Listing itineraries. Filter: {}", filter);
		return itineraryService.list(filter, pageable);
	}
	
}
