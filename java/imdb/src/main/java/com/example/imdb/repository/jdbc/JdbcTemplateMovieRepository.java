package com.example.imdb.repository.jdbc;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        Movie movie = jdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID,
                new BeanPropertyRowMapper<Movie>(Movie.class),
                new Object[]{id});

        return Optional.ofNullable(movie);
    }

    @Override
    public List<Movie> finAll(int pageNo, int pageSize) {
        int offset = pageNo*pageSize;
        return jdbcTemplate.query(SELECT_MOVIES_PAGINATION,
                new Object[]{offset, pageSize},
                new BeanPropertyRowMapper<Movie>(Movie.class));


    }

    @Override
    public Stream<Movie> findAllByYearBetween(int from, int to) {
        return jdbcTemplate.query(SELECT_MOVIES_BY_YEAR_BETWEEN, new Object[] {from, to},
                new BeanPropertyRowMapper<Movie>(Movie.class)).stream();

    }
    @Override
    @Transactional()
    public Movie save(Movie movie) {
        jdbcTemplate.update(INSERT_INTO_MOVIES,
                new Object[] {
                        movie.getTitle(),
                        movie.getYear(),
                        movie.getImdb()
                });
        return movie;
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
        return removeById(movie.getMovieID());

    }

}
