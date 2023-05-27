package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.exampl.demo.model.CatalogItem;
import com.exampl.demo.model.UserMovieRating;
import com.example.demo.service.MovieInfo;
import com.example.demo.service.MovieRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	WebClient.Builder builder;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	MovieRating movieRating;
	
	@GetMapping("/{userId}")
//	@HystrixCommand(fallbackMethod="getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		
		//Get all rated movie IDs
		UserMovieRating ratings = movieRating.getUserRating(userId);
		
		//For each movie Id, call movie info service and get details
		return ratings.getUserMovieRating().stream().map(rating -> {
			
//			Movie movie = builder.build()
//				.get()
//				.uri("http://localhost:8081/movies/"+rating.getMovieId())
//				.retrieve()
//				.bodyToMono(Movie.class)
//				.block();
			return movieInfo.getCatalogItem(rating);
					
		}).collect(Collectors.toList());
		
		//Put them all together
	
	}
	
public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId){
		
		return Arrays.asList(new CatalogItem("No data found","",0));
	}
	
		
	
		
}
