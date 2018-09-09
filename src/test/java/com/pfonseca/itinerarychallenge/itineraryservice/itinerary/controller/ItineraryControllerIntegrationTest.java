package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfonseca.itinerarychallenge.itineraryservice.ItineraryServiceApplication;
import com.pfonseca.itinerarychallenge.itineraryservice.city.domain.City;
import com.pfonseca.itinerarychallenge.itineraryservice.city.repository.CityRepository;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.domain.Itinerary;
import com.pfonseca.itinerarychallenge.itineraryservice.itinerary.repository.ItineraryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ItineraryServiceApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ItineraryControllerIntegrationTest {

	
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ItineraryRepository repository;
    
    @Autowired
    private CityRepository cityRepository;
    
	@Autowired
	private ObjectMapper objectMapper;

	private City zaragozaCity;
	private City madriCity;
	private City barcelonaCity;

	private Itinerary itineraryZaragozaToMadri;
	private Itinerary itineraryMadriToBarcelona;
    
    @Before
    public void setup() {
    	
    	zaragozaCity = cityRepository.saveAndFlush(new City("Zaragoza"));
    	madriCity = cityRepository.saveAndFlush(new City("Madri"));
    	barcelonaCity = cityRepository.saveAndFlush(new City("Barcelona"));
    	
    	itineraryZaragozaToMadri = new ItineraryBuilder().withDefaultValues().withOrigin(zaragozaCity).withDestiny(madriCity).build();
    	itineraryMadriToBarcelona = new ItineraryBuilder().withDefaultValues().withOrigin(madriCity).withDestiny(barcelonaCity).build();
    	
    	repository.saveAndFlush(itineraryZaragozaToMadri);
    	repository.saveAndFlush(itineraryMadriToBarcelona);
    }
    
    @After
    public void resetDb() {
    	repository.deleteAll();
    	cityRepository.deleteAll();
    }
	
    @Test
    public void givenItineraries_whenGetItineraries_thenStatus200() throws Exception {

        mvc.perform(get("/itineraries/").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(2))))
          .andExpect(jsonPath("$.content[0].origin.name", is(zaragozaCity.getName())));
    }
    
    @Test
    public void givenItineraries_whenGetItinerariesUsingOriginFilter_thenStatus200() throws Exception {
    	
    	mvc.perform(get("/itineraries/?origin=" + madriCity.getId()).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$.content", hasSize(equalTo(1))))
    	.andExpect(jsonPath("$.content[0].origin.name", is(madriCity.getName())));
    }
    
    @Test
    public void givenItineraries_whenGetItinerariesUsingDestinyFilter_thenStatus200() throws Exception {
    	
    	mvc.perform(get("/itineraries/?destiny=" + madriCity.getId()).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$.content", hasSize(equalTo(1))))
    	.andExpect(jsonPath("$.content[0].destiny.name", is(madriCity.getName())));
    }
    
    @Test
    public void givenItineraries_whenGetNoItinerariesUsingDepartureTimeFilter_thenStatus200() throws Exception {
    	
    	mvc.perform(get("/itineraries/?departureAfter=23:50:00").contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$.content", hasSize(equalTo(0))));
    }
    
    @Test
    public void givenItineraries_whenGetItinerariesUsingDepartureTimeFilter_thenStatus200() throws Exception {
    	
    	mvc.perform(get("/itineraries/?departureAfter=00:00:00").contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))));
    }
    
    @Test
    public void givenItinerary_whenGetByValidId_thenStatus200() throws Exception {
    	
    	mvc.perform(get("/itineraries/"+this.itineraryZaragozaToMadri.getId()).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$.id", is(this.itineraryZaragozaToMadri.getId().intValue())));
    }
    
    @Test
    public void givenItinerary_whenGetByInvalidId_thenStatus404() throws Exception {
    	
    	mvc.perform(get("/itineraries/"+99999L).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isNotFound());
    }
    
    @Test
    public void givenDeletingItinerary_whenGetByValidId_thenStatus200() throws Exception {
    	
    	mvc.perform(delete("/itineraries/"+this.itineraryZaragozaToMadri.getId()).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk());
    	
    	//Check if its really deleted
    	mvc.perform(get("/itineraries/"+this.itineraryZaragozaToMadri.getId()).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isNotFound());
    }
    
    @Test
    public void givenDeletingItinerary_whenGetByInvalidId_thenStatus404() throws Exception {
    	
    	mvc.perform(delete("/itineraries/"+99999L).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isNotFound());
    }
	
    
    @Test
    public void givenSavingItinerary_whenEntityIsValid_thenStatus201() throws Exception {
    	
    	itineraryMadriToBarcelona.setId(null);
    	
    	mvc.perform(post("/itineraries/")
    			.content(objectMapper.writeValueAsString(itineraryMadriToBarcelona))
    			.contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isCreated())
    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void givenSavingItinerary_whenEntityIsInValid_thenStatus400() throws Exception {
    	
    	itineraryMadriToBarcelona.setId(null);
    	itineraryMadriToBarcelona.setDepartureTime(LocalTime.MAX);
    	
    	mvc.perform(post("/itineraries/")
    			.content(objectMapper.writeValueAsString(itineraryMadriToBarcelona))
    			.contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isBadRequest());
    }
	
    
    @Test
    public void givenUpdatingItinerary_whenEntityIsValid_thenStatus200() throws Exception {
    	
    	itineraryMadriToBarcelona.setDestiny(zaragozaCity);
    	
    	mvc.perform(put("/itineraries/"+itineraryMadriToBarcelona.getId())
    			.content(objectMapper.writeValueAsString(itineraryMadriToBarcelona))
    			.contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    	
    	
    	//Checking if destiny city changed to zaragoza
    	mvc.perform(get("/itineraries/"+this.itineraryMadriToBarcelona.getId()).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$.destiny.id", is(this.zaragozaCity.getId().intValue())));
    }
    
    @Test
    public void givenUpdatingItinerary_whenEntityIsNotFound_thenStatus404() throws Exception {
    	
    	mvc.perform(put("/itineraries/"+99990L)
    			.content(objectMapper.writeValueAsString(itineraryMadriToBarcelona))
    			.contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isNotFound());
    	
    }
    
    @Test
    public void givenUpdatingItinerary_whenAnInvalidEntity_thenStatus400() throws Exception {
    	
    	itineraryMadriToBarcelona.setDepartureTime(LocalTime.MAX);
    	
    	mvc.perform(put("/itineraries/"+itineraryMadriToBarcelona.getId())
    			.content(objectMapper.writeValueAsString(itineraryMadriToBarcelona))
    			.contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isBadRequest());
    	
    }
}
