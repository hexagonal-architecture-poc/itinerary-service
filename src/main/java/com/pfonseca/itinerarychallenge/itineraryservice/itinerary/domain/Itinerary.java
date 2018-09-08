package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Itinerary between two cities")
public class Itinerary {

	@Id
	@SequenceGenerator(name = "SEQ_ITINERARY", sequenceName = "SEQ_ITINERARY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITINERARY")
	@ApiModelProperty(notes = "The database generated itinerary ID")
	private Long id;
	
	@Valid
	@NotNull
	@ManyToOne
	@ApiModelProperty(notes = "Origin city", required=true)
	private City origin;
	
	@Valid
	@NotNull
	@ManyToOne
	@ApiModelProperty(notes = "Destiny city", required=true)
	private City destiny;
	
	@NotNull
	@ApiModelProperty(notes = "Departure time. Format: \"HH:mm:ss\"", required=true)
	private LocalTime departureTime;
	
	@NotNull
	@ApiModelProperty(notes = "Arrival time. Format: \"HH:mm:ss\"", required=true)
	private LocalTime arrivalTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getOrigin() {
		return origin;
	}

	public void setOrigin(City origin) {
		this.origin = origin;
	}

	public City getDestiny() {
		return destiny;
	}

	public void setDestiny(City destiny) {
		this.destiny = destiny;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}
