package com.alvarodelaflor.sensors.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomService {

    private static Random random = new Random();

    public Long getRandomLong(Long lowerBound, Long upperBound) {
        Long randomValue = lowerBound + (long) (random.nextDouble() * (upperBound - lowerBound + 1));
        return  randomValue;
    }

    public Double getRandomDouble(Double lowerBound, Double upperBound) {
        Double randomValue = lowerBound + (random.nextDouble() * (upperBound - lowerBound + 1));
        return Math.round(randomValue * 100.0) / 100.0;
    }
}
