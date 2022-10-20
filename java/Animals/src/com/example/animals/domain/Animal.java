package com.example.animals.domain;

public abstract class Animal {
    private int legs;

    public Animal(int legs) {
        this.legs = legs;
    }
    public int getLegs() {
        return legs;
    }
    public void walk() {
        System.out.println(
                String.format("Hayvan şuanda %d yürüyor", legs));

    }
    public abstract void eat();


}
