package com.exapmle.app;

import com.example.lottery.service.LotteryService;

import java.util.ServiceLoader;

public class LotteryApp {
    public static void main(String[] args) {
        LotteryService lotterySrv;

        ServiceLoader<LotteryService> services = ServiceLoader.load(LotteryService.class);
        lotterySrv = services.findFirst().get();
        lotterySrv.draw(1, 49, 6, 10).forEach(System.out::println);
    }
}
