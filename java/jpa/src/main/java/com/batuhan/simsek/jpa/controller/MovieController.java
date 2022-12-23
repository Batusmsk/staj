package com.batuhan.simsek.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.batuhan.simsek.jpa.dto.MovieDto;
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
    
    @GetMapping(value = "/movie/{movieId}")
	public Optional<Movie> getMovieById(@PathVariable("movieId") Integer movieId){
		return movieService.getMovie(movieId);
	}
    
    @PostMapping(value = "/movie")
	public Movie saveMovie(@RequestBody MovieDto movie){
		return movieService.saveMovie(movie);
	}
}

