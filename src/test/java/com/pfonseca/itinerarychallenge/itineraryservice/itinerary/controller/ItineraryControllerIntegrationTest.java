package com.pfonseca.itinerarychallenge.itineraryservice.itinerary.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	
	
}
