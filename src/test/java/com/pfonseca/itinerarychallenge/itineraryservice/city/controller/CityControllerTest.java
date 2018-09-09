package com.pfonseca.itinerarychallenge.itineraryservice.city.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ItineraryServiceApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CityControllerTest {

	
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private CityRepository cityRepository;
    
    private City city;
    
    
    @Before
    public void setup() {
    	city = new City("Paris");
    	cityRepository.saveAndFlush(city);
    }
    
    
    @Test
    public void givenCities_whenGetCities_thenStatus200() throws Exception {

        mvc.perform(get("/cities/").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(2))));
        
    }
    
    @Test
    public void givenCity_whenGetAValidCity_thenStatus200() throws Exception {
    	
    	mvc.perform(get("/cities/"+this.city.getId()).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$.name", is(city.getName())));
    	
    }
    
    @Test
    public void givenCity_whenGetAnInvalidCity_thenStatus404() throws Exception {
    	
    	mvc.perform(get("/cities/"+999999L).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isNotFound());
    	
    }
	
}
