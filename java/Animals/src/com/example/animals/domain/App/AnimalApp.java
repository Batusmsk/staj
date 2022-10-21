package com.example.animals.domain.App;

import com.example.animals.domain.*;

import java.util.Arrays;
import java.util.List;

public class AnimalApp {
    public static void main(String[] args) {
        List<Animal> animals = Arrays.asList(
                new Cat(),
                new Spider(),
                new Cat("Tekir"),
                new Fish("Memo"),
                new Spider(),
                new Fish("Çupra")
        );

        long totalNumOfLegs = animals.stream().filter(animal -> animal instanceof Pet).mapToLong(Animal::getLegs).sum();

        System.out.println("Evcil hayvanların toplam bacak sayısı: " + totalNumOfLegs);

        for(Animal animal : animals) {
            animal.walk();
            animal.eat();
            if(animal instanceof Pet) {
                ((Pet)animal).play();

            }
            System.out.println(animal.toString());
        }
    }
}
