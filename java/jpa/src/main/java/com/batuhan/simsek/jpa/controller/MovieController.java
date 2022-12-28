package com.batuhan.simsek.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.batuhan.simsek.jpa.dto.MovieDto;
import com.batuhan.simsek.jpa.dto.UpdateMovieRequest;
import com.batuhan.simsek.jpa.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	MovieService movieService;
	
    @GetMapping(value = "/movies")
	public ResponseEntity<List<MovieDto>> getMovies(){
		return new ResponseEntity<List<MovieDto>>(movieService.getMovies(),HttpStatus.OK);
	}
    
    @GetMapping(value = "/movie/{movieId}")
	public MovieDto getMovieById(@PathVariable("movieId") Integer movieId){
		return movieService.getMovie(movieId);
	}
    
    @DeleteMapping(value = "/movie/{movieId}")
    public void deleteMovie(@PathVariable("movieId") Integer movieId) {
    	movieService.deleteMovie(movieId);
    }
    
    @PutMapping(value = "/movie")
    public void updateMovie(@RequestBody UpdateMovieRequest input) {
    	
    	movieService.updateMovie(input);
    }
    @PostMapping(value = "/movie")
	public Boolean saveMovie(@RequestBody MovieDto movie){
		return movieService.saveMovie(movie);
	}
}

