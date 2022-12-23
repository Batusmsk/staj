package com.example.imdb;

import com.example.imdb.repository.MovieRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.imdb.config.DatabaseConfig;

public class App {
    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(DatabaseConfig.class);
            MovieRepository repo = container.getBean(MovieRepository.class);
            repo.findById(1).ifPresent(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }

        //repo.finAll(4, 10).forEach(System.out::println);
        //repo.findAllByYearBetween(1970, 1979).forEach(System.out::println);
        //Movie movie = new Movie("My movie 3", 2018, "tt123456");
        //repo.save(movie);
        // container.close();
    }
}
