package com.example;

import java.util.HashSet;
import java.util.Set;

public class StudySet {

    public static void main(String[] args) {


        Set<Integer> numbers = new HashSet<>();
        Object n = numbers;
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println(n);
    }

}
