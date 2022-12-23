package com.batuhan.simsek.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.simsek.jpa.entity.Movie;
import com.batuhan.simsek.jpa.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;

	public List<Movie> getMovies() {

		return movieRepository.findAll();

	}

	public Optional<Movie> getMovie(Integer id) {

		return movieRepository.findById(id);

	}

}
