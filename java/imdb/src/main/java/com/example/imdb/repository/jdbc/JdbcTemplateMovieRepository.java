package com.example.imdb.repository.jdbc;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class JdbcTemplateMovieRepository implements MovieRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Optional<Movie> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Movie> finAll(int pageNo, int pageSize) {
        return null;
    }

    @Override
    @Transactional()
    public Movie save(Movie movie) {
        return null;
    }

    @Override
    public Movie update(Movie movie) {
        return null;
    }

    @Override
    public Optional<Movie> removeById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Movie> remove(Movie movie) {
        return Optional.empty();
    }

    @Override
    public Stream<Movie> findAllByYearBetween(int from, int to) {
        return null;
    }
}
