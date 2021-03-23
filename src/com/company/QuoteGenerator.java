// Uses a Poisson Process to generate fake Quotes

package com.company;

import java.util.Random;

public class QuoteGenerator {
    private PoissonProcess poissonProcess;
    private Random randomGenerator;
    private static final Double AVERAGE_QUOTES_PER_SECOND_RATE = 0.5;
    private static final Integer MIN_REQUEST_SIZE = 100000;
    private static final Integer REQUEST_STEP_SIZE = 100000;
    private static final Integer RANDOM_SIZE_RANGE = 100;

    QuoteGenerator() {
        this.poissonProcess = new PoissonProcess(AVERAGE_QUOTES_PER_SECOND_RATE);
        this.randomGenerator = new Random();
    }

    public Boolean hasOrderArrived() {
        return this.poissonProcess.isNewArrival();
    }

    public QuoteRequest generateQuoteRequest() {
        Integer size = MIN_REQUEST_SIZE + REQUEST_STEP_SIZE * randomGenerator.nextInt(RANDOM_SIZE_RANGE);
        Integer direction = (2 * randomGenerator.nextInt(2)) - 1; //Ensures in {-1, +1}
        return new QuoteRequest(size, direction);
    }
}
