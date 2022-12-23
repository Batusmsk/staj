package com.batuhan.simsek.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.simsek.jpa.dto.ActorDto;
import com.batuhan.simsek.jpa.dto.MovieDto;
import com.batuhan.simsek.jpa.entity.Actor;
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
	
	public Movie saveMovie(MovieDto moviedto) {
		Movie movie = new Movie();
		List<Actor> actors = new ArrayList<Actor>();
		for(ActorDto actorDto:moviedto.getActors()) {
			Actor actor = new Actor();
			actor.setName(actorDto.getName());
			actor.setLastName(actorDto.getLastName());
			actor.setMovie(movie);
			actors.add(actor);
		}
		movie.setImdb(moviedto.getImdb());
		movie.setTitle(moviedto.getTitle());
		movie.setYear(moviedto.getYear());
		movie.setActor(actors);
		return movieRepository.save(movie);
	}

}
