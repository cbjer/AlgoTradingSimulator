package com.company;

import java.util.Random;

public class PoissonProcess {
    private Double lambda;
    private Double nextWaitTimeSeconds;
    private long previousEventTime;
    private Random uniformRandomGenerator;

    private static final Double NANO_SECONDS_IN_SECOND = 1e9;

    PoissonProcess(Double lambda) {
        // Lambda is expected number of arrivals per second
        this.lambda = lambda;
        this.uniformRandomGenerator = new Random();
        this.previousEventTime = System.nanoTime();
        this.nextWaitTimeSeconds = sampleFromExponentialDistribution();
    }

    public Boolean isNewArrival() {
        Double currentWaitTime = getCurrentWaitTimeInSeconds();

        if (currentWaitTime > this.nextWaitTimeSeconds) {
            updatePreviousEventTime();
            generateNextWaitTime();
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }
    }

    private Double sampleFromExponentialDistribution() {
        // Uses inverse transform sampling to sample from exponential
        Double uniformRandom = this.uniformRandomGenerator.nextDouble();
        return (Math.log(1 - uniformRandom) / (this.lambda * -1.0));
    }

    private void updatePreviousEventTime() {
        this.previousEventTime = System.nanoTime();
    }

    private Double getCurrentWaitTimeInSeconds() {
        Double waitingTimeNanoSeconds = (double) System.nanoTime() - this.previousEventTime;
        return waitingTimeNanoSeconds / NANO_SECONDS_IN_SECOND;
    }

    private void generateNextWaitTime() {
        this.nextWaitTimeSeconds = sampleFromExponentialDistribution();
    }
}
