package com.batuhan.simsek.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batuhan.simsek.jpa.entity.Movie;
import com.batuhan.simsek.jpa.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	MovieService movieService;
	
    @GetMapping(value = "/movies")
	public List<Movie> getMovies(){
		return movieService.getMovies();
	}
}
