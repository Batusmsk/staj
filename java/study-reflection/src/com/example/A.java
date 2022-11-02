package com.example;

public class A {
    private int x;

    public A(int x) {
        this.x = x;
    }

    public int fun() {return 2*x;}
    protected void gun() {
        System.out.println("gun() is running...");
    }
}
