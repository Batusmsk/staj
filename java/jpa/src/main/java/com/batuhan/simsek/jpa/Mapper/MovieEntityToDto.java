package com.batuhan.simsek.jpa.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.batuhan.simsek.jpa.dto.MovieDto;
import com.batuhan.simsek.jpa.entity.Movie;

public class MovieEntityToDto {
	
	public MovieDto convert(Movie movie) {
		MovieDto moviedto = new MovieDto();
		ActorEntityToDto converter = new ActorEntityToDto();
		moviedto.setId(movie.getMovieID());
		moviedto.setImdb(movie.getImdb());
		moviedto.setTitle(movie.getTitle());
		moviedto.setYear(movie.getYear());
		moviedto.setActors(converter.convert(movie.getActor()));
		return moviedto;
	}

	public List<MovieDto> convert(List<Movie> movies) {
		List<MovieDto> returnList=new ArrayList<>();
		for(Movie movie:movies) {
			returnList.add(convert(movie));
		}
		return returnList;
	}
	

}
