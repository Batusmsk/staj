package com.example;

import com.example.imdb.service.MovieService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext container = new ClassPathXmlApplicationContext("spring-config.xml")){
            MovieService movieSrv = container.getBean(MovieService.class);
            movieSrv.findAllMoviesByYearRange(1970, 1979).forEach(System.out::println);
        }

    }
}
