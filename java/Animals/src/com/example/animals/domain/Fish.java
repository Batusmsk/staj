package com.example.animals.domain;

public class Fish extends Animal implements Pet{
    private String name;

    public Fish (String name) {
        super(0);
        this.name = name;
    }

    @Override
    public void eat() {
        System.out.println(String.format("%s Yemek yiyor", name));
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void play() {
        System.out.println(String.format("%s Haraket ediyor", name));
    }

    @Override
    public void walk() {
        System.out.println(String.format("%s Yüzüyor", name));

    }
}
