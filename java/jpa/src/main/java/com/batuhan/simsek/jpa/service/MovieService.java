package com.batuhan.simsek.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.simsek.jpa.Mapper.MovieEntityToDto;
import com.batuhan.simsek.jpa.dto.ActorDto;
import com.batuhan.simsek.jpa.dto.MovieDto;
import com.batuhan.simsek.jpa.dto.UpdateMovieRequest;
import com.batuhan.simsek.jpa.entity.Actor;
import com.batuhan.simsek.jpa.entity.Movie;
import com.batuhan.simsek.jpa.repository.MovieRepository;


@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;

	public List<MovieDto> getMovies() {

		MovieEntityToDto converter=new MovieEntityToDto();
		return converter.convert(movieRepository.findAll());

	}

	public MovieDto getMovie(Integer id) {
		MovieEntityToDto converter=new MovieEntityToDto();
		return converter.convert(movieRepository.findById(id).orElse(new Movie()));
		
	}
	
	public void deleteMovie(Integer id) { 
		movieRepository.deleteById(id);	
	}
	
	public boolean updateMovie(UpdateMovieRequest input) { 
		Movie movie = movieRepository.findById(input.getId()).orElse(new Movie());
		movie.setImdb(input.getImdb());
		movie.setTitle(input.getTitle());
		movie.setYear(input.getYear());
		movieRepository.save(movie);
		return true;

	}
	
	public Boolean saveMovie(MovieDto moviedto) {
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
		var ret=movieRepository.save(movie);
		
		return true;
	}

}
