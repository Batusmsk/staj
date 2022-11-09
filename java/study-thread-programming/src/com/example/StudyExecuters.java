package com.example;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudyExecuters {

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(cores);

        for (int i = 0; i < 1000; i++) {
            es.submit(() -> System.out.println("Hello World"));
        }

        es.shutdown();
    }
}
