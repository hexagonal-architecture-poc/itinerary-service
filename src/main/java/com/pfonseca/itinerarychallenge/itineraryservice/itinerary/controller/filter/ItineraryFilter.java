package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller.filter;

import org.springframework.data.jpa.domain.Specification;

import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;

public class ItineraryFilter {

	private Long origin;
	private Long destiny;

	public Specification<Itinerary> buildQuery() {
		return Specification
				.where(withOriginId(origin))
					.or(withDestinyId(destiny));

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

}