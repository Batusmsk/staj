package com.example;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

public class StudyCallableThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LotteryTask task = new LotteryTask();
        FutureTask<List<Integer>> future = new FutureTask<>(task);
        Thread t = new Thread(future);
        t.start();

        future.get().forEach(System.out::println);
        System.out.println("Done.");
    }

    static class LotteryTask implements Callable<List<Integer>> {

        @Override
        public List<Integer> call() throws Exception {
            return new Random().ints(1, 50).distinct().limit(6).sorted().boxed().collect(Collectors.toList());
        }
    }
}
