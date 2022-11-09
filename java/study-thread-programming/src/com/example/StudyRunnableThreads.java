package com.example;

import java.util.concurrent.atomic.AtomicInteger;

public class StudyRunnableThreads {
    public static void main(String[] args) throws InterruptedException {
        LotterySynchronizedTask task = new LotterySynchronizedTask();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        long start = System.nanoTime();

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        long stop = System.nanoTime();
        System.err.println((stop - start) + " : " + task.getCounter());
    }

    static class LotteryAtomicTask implements Runnable {
        private AtomicInteger counter = new AtomicInteger(0);

        @Override
        public void run() {
            for (int i = 0; i < 100000; ++i) {
                counter.getAndIncrement();

            }
        }
    }

    static class LotterySynchronizedTask implements Runnable {
        private int counter;

        @Override
        public void run() {
            for (int i = 0; i < 100000; ++i) {

                ++counter;

            }
        }

        public int getCounter() {
            return counter;
        }
    }
}


