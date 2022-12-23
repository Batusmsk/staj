package com.example.imdb;

import com.batuhan.simsek.jpa.repository.MovieRepository;
import com.example.imdb.config.DatabaseConfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
            ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(DatabaseConfig.class);
           // MovieRepository repo = container.getBean(MovieRepository.class);
           // repo.findById(1).ifPresent(System.out::println);


        //repo.finAll(4, 10).forEach(System.out::println);
        //repo.findAllByYearBetween(1970, 1979).forEach(System.out::println);
        //Movie movie = new Movie("My movie 3", 2018, "tt123456");
        //repo.save(movie);
        // container.close();
    }
}
