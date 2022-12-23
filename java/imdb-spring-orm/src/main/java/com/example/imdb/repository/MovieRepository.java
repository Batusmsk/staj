package com.example.imdb.repository;

import com.example.imdb.entity.Movie;

import java.util.stream.Stream;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    String SELECT_MOVIE_BY_ID = "SELECT MOVIEID,TITLE,YEAR,IMDB FROM MOVIES WHERE MOVIEID=?";
    String SELECT_MOVIES_PAGINATION = "SELECT MOVIEID,TITLE,YEAR,IMDB FROM MOVIES LIMIT ?,?";
    String SELECT_MOVIES_BY_YEAR_BETWEEN = "SELECT MOVIEID,TITLE,YEAR,IMDB FROM MOVIES WHERE YEAR BETWEEN ? AND ?";
    String INSERT_INTO_MOVIES = "INSERT INTO MOVIES(MOVIEID,TITLE,YEAR,IMDB) VALUES(NULL, ?,?,?)";
    Stream<Movie> findAllByYearBetween(int from, int to);

}
