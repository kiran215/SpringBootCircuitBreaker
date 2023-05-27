package com.example.demo.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Rating;
import com.example.demo.Model.UserMovieRating;

@RestController
@RequestMapping("/rating")
public class MovieRating {
	
	
//	@GetMapping("/{movieId}")
//	public Rating getRating(@PathVariable("movieId") String movieId) {
//		
//		
//		return new Rating(movieId,4);
//	}
	
	@GetMapping("/{userId}")
	public UserMovieRating getRatingByUserId(@PathVariable("userId") String userId) throws InterruptedException {
		Thread.sleep(4000);
		return new UserMovieRating(Arrays.asList(new Rating("1",4),new Rating("2",3),new Rating("3",5)));
	}

}
