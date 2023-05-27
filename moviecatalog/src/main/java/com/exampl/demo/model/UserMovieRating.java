package com.exampl.demo.model;

import java.util.List;

public class UserMovieRating {

	
	private List<Rating> userMovieRating;

	public List<Rating> getUserMovieRating() {
		return userMovieRating;
	}

	public void setUserMovieRating(List<Rating> userMovieRating) {
		this.userMovieRating = userMovieRating;
	}

	public UserMovieRating(List<Rating> userMovieRating) {
		super();
		this.userMovieRating = userMovieRating;
	}

	public UserMovieRating() {
		super();
	}
	
	
	
}
