package com.example.lottery.service.business;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.Service;
import com.example.numgen.service.RandomNumberService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service(ServiceQualtiy.FAST)
public class SimpleLotteryService implements LotteryService {


    private RandomNumberService rns;

    public SimpleLotteryService() {
        ServiceLoader<RandomNumberService> services = ServiceLoader.load(RandomNumberService.class);
        rns = services.findFirst().get();

    }

    @Override
    public List<Integer> draw(int min, int max, int size) {
        return IntStream.generate(() -> rns.createRandomNumber(min, max))
                .distinct()
                .limit(size)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }
}
