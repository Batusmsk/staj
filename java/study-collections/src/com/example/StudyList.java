package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Collections.sort;

public class StudyList {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();

        numbers.add(5);
        numbers.add(25);
        numbers.add(52);
        numbers.add(2);
        numbers.add(2);

        System.out.println(numbers);
        sort(numbers); // Kucukten buyuge
        System.out.println("< " + numbers);

        // Anonymous class
        sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        }); // Buyukten kucuge

        System.out.println("> " +numbers);

        numbers.forEach((Integer n) -> System.out.println(n));
        System.out.println("---------");
        Consumer<Integer> yazdir = System.out::println;
        numbers.forEach(yazdir);

    }

}
