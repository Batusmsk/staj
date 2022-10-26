package com.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class StudyDeque {
    public static void main(String[] args) {
        Deque<Integer> numbers = new ArrayDeque<>();
        numbers.addFirst(10); // numbers.add(0,10);
        numbers.addLast(100);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(6);

        numbers.offer(10);
        numbers.getFirst(); // numbers.get(0);
        numbers.getLast(); // numbers.get(numbers.size() -1);
        numbers.removeFirst(); // numbers.remove(0);
        numbers.removeLast(); // numbers.remove(numbers.size() -1);
        numbers.peek(); // sadece okur, silmez.
        // FIFO: First in first out
        numbers.poll();
        numbers.offer(10);

    }

}
