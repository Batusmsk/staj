package com.example.lottery.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface LotteryService {

    List<Integer> draw(int min, int max, int size);

    default List<List<Integer>> draw(int min, int max, int size, int n) {
        return IntStream.range(0, n).mapToObj(i -> this.draw(min, max, size))
                .collect(Collectors.toList());
    }
}
