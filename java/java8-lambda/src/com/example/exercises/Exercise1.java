package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

import java.util.Comparator;
import java.util.List;

public class Exercise1 {
    public static void main(String[] args) {
        WorldDao worldDao = InMemoryWorldDao.getInstance();
        List<Country> countries = worldDao.findAllCountries();
        countries.stream().sorted(Comparator.comparing(Country::getGnp).reversed())
                .limit(10)
                .map(Country::getName)
                .forEach(System.out::println);

    }
}
