package com.example.exercises;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercise4 {

    public static void main(String[] args) {
        MovieService movieSrv = InMemoryMovieService.getInstance();
        var movies = movieSrv.findAllMovies();

        var genres = movies.stream().map(Movie::getGenres).flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        genres.forEach(Entry -> System.out.println(Entry.getId() + " : " + Entry.getName()));

    }

}
