package com.batuhan.simsek.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batuhan.simsek.jpa.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {}
