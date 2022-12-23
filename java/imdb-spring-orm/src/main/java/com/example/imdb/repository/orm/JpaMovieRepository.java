package com.example.imdb.repository.orm;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class JpaMovieRepository implements MovieRepository {
@PersistenceContext
private EntityManager em;

    @Override
    public Optional<Movie> findById(Integer id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    @Override
    public List<Movie> finAll(int pageNo, int pageSize) {
        return null;
    }

    @Override
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
