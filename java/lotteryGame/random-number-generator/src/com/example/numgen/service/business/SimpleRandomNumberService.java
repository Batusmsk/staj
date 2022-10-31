package com.example.numgen.service.business;

import com.example.numgen.service.RandomNumberService;

import java.security.SecureRandom;
import java.util.Random;

public class SimpleRandomNumberService implements RandomNumberService {
    private Random random = new SecureRandom();

    @Override
    public int createRandomNumber(int min, int max) {
        return random.nextInt(max-min+1)+min;
    }

    @Override
    public boolean createRandomBoolean() {
        return random.nextBoolean();
    }
}
