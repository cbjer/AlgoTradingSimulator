package com.company;

public abstract class MarketSpreadProcess {
    protected RandomWalk randomWalk;
    private static final Double BASES_POINTS_FACTOR = 10000.0;

    public Double getLatestSpread() {
        this.randomWalk.updateRandomWalk();
        return this.randomWalk.getLatestLevel() * BASES_POINTS_FACTOR;
    }
}
