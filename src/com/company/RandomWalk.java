// Implements a Ornstein–Uhlenbeck random walk process (random walk with mean reversion)

package com.company;

import java.util.Random;

public class RandomWalk{
    private Double meanReversionFactor;
    private Double drift;
    private Double sigma;
    private long timeOfLastSample;
    private Double latestLevel;
    private Random randomGenerator;

    private static final Double NANO_SECONDS_TO_DAYS = 1e9 * 60 * 60 * 24.0;

    RandomWalk(Double meanReversionFactor, Double drift, Double sigma, Double initialValue) {
        this.meanReversionFactor = meanReversionFactor;
        this.drift = drift;
        this.sigma = sigma;
        this.latestLevel = initialValue;
        this.randomGenerator = new Random();
        updateTimeLastSample();
    }

    public void updateRandomWalk() {
        this.latestLevel = nextOrsteinLevel();
        updateTimeLastSample();
    }

    public Double getLatestLevel() {
        return this.latestLevel;
    }

    private Double getTimeSinceLastUpdateDays() {
        Double timeSinceLastUpdateNanoSeconds = (double) System.nanoTime() - this.timeOfLastSample;
        return timeSinceLastUpdateNanoSeconds / NANO_SECONDS_TO_DAYS;
    }

    private void updateTimeLastSample() {
        this.timeOfLastSample = System.nanoTime();
    }

    private Double nextOrsteinLevel() {
        // Applies the Ornstein-Uhlenbeck Update Equation
        Double dt = getTimeSinceLastUpdateDays();
        Double previousLevel = this.latestLevel;
        Double standardNormal = this.randomGenerator.nextGaussian();
        Double nextLevel = previousLevel + (this.drift - this.meanReversionFactor * previousLevel) * dt +
                this.sigma * Math.sqrt(dt) * standardNormal;
        return nextLevel;
    }
}
