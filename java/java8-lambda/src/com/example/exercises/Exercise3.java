package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercise3 {

    public static void main(String[] args) {

    var worldDao = InMemoryWorldDao.getInstance();
    var countries = worldDao.findAllCountries();

    var continentCountryCounts = countries.stream()
            .collect(Collectors.groupingBy(Country::getContinent, Collectors.counting()));
    continentCountryCounts.forEach((continent, count) -> System.out.println(continent + " : " + count));

    List<Map.Entry<String, Long>> counts = new ArrayList<>(continentCountryCounts.entrySet());
    counts.stream().sorted((e1,e2) -> (int)(e1.getValue()- e2.getValue())).forEach(
            entry -> System.out.println(entry.getKey() + " : " + entry.getValue())
    );

    }


}
