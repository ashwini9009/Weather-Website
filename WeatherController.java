package com.project.weatherproject.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.weatherproject.model.WeatherResponse;
import com.project.weatherproject.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200")//allow angular to access

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Value("${openweathermap.api.key}")
	private String apiKey;
	
	 @Autowired
	    private WeatherService weatherService;

	    @GetMapping
	    public ResponseEntity<?> getWeather(@RequestParam String city) {
	        try {
	            WeatherResponse response = weatherService.getWeather(city);
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("City not found or invalid API key");
	        }
	    }
		
}
	


