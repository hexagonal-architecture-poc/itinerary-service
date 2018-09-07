package com.pfonseca.itinerarychallenge.itineraryservice.city.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class City {

	@Id
	@SequenceGenerator(name = "SEQ_CITY", sequenceName = "SEQ_CITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CITY")
	private Long id;
	
	@NotBlank
	@Size(max = 255)
	private String name;
	
	public City() {}
	
	public City(Long id, String name) {
		this();
		
		this.id = id;
		this.name = name;
	}
	
	public City(String name) {
		this(null, name);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
