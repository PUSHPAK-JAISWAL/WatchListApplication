package com.Watchlist.application.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {

	String apiUrl = "http://www.omdbapi.com/?apikey=7a5567d4&t=";
	
	public String getMovieRating(String title) {
		try {
			RestTemplate template = new RestTemplate();
			
			ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl+title, ObjectNode.class);
			
			ObjectNode jsonObject = response.getBody();
			
			return jsonObject.path("imdbRating").asText();
		}
		catch(Exception e) {
			return null;
		}
	}
}
