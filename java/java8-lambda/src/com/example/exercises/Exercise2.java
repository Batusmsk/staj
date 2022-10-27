package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public class Exercise2 {
    public static void main(String[] args) {

        WorldDao worldDao = InMemoryWorldDao.getInstance();
        List<Country> countries = worldDao.findAllCountries();
        List<String> continents = countries.stream().map(Country::getContinent)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        continents.forEach(System.out::println);

        String csvContinents = countries.stream().map(Country::getContinent)
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
        System.out.println(csvContinents);

    }

}
