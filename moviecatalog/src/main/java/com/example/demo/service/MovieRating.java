package com.example.demo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exampl.demo.model.Rating;
import com.exampl.demo.model.UserMovieRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieRating {
	
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod="getFallbackUserRating",commandProperties= {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")
			
	})
	public UserMovieRating getUserRating(String userId) {
		return restTemplate.getForObject("http://MOVIE-RATING/rating/"+userId, UserMovieRating.class);

	}

	public UserMovieRating getFallbackUserRating(String userId){
		UserMovieRating userMovieRating = new UserMovieRating();
		
		userMovieRating.setUserMovieRating(Arrays.asList(new Rating("0",0)));
		
		return userMovieRating;
	}

}
