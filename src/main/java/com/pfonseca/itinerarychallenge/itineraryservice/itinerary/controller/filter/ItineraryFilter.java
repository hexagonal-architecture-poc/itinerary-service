package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.filter;

import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;

public class ItineraryFilter {

	private Long origin;
	private Long destiny;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime departureAfter;

	public Specification<Itinerary> buildQuery() {
		return Specification
				.where(withOriginId(origin))
					.and(withDestinyId(destiny))
					.and(afterTime(departureAfter));

	}

	private Specification<Itinerary> afterTime(LocalTime departure) {
		return (root, query, cb) -> {
			
			if(departure == null)
				return null;
			
			return cb.greaterThanOrEqualTo(root.get("departureTime"), departure);
		};
	}

	private Specification<Itinerary> withOriginId(Long originId) {
		return (root, query, cb) -> {
			
			if(originId == null)
				return null;
			
			return cb.equal(root.get("origin").get("id"), originId);
		};
	}
	
	private Specification<Itinerary> withDestinyId(Long destinyId) {
		return (root, query, cb) -> {
			
			if(destinyId == null)
				return null;
			
			return cb.equal(root.get("destiny").get("id"), destinyId);
		};
	}

	public Long getOrigin() {
		return origin;
	}

	public void setOrigin(Long origin) {
		this.origin = origin;
	}

	public Long getDestiny() {
		return destiny;
	}

	public void setDestiny(Long destiny) {
		this.destiny = destiny;
	}

	public LocalTime getDepartureAfter() {
		return departureAfter;
	}

	public void setDepartureAfter(LocalTime departureAfter) {
		this.departureAfter = departureAfter;
	}

	@Override
	public String toString() {
		return "ItineraryFilter [origin=" + origin + ", destiny=" + destiny + ", departureAfter=" + departureAfter
				+ "]";
	}

}