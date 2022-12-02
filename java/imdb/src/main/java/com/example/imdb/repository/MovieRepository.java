package com.example.imdb.repository;

import com.example.imdb.entity.Movie;

import java.util.stream.Stream;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Stream<Movie> findAllByYearBetween(int from, int to);

}
