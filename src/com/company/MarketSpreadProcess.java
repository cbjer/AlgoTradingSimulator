package com.company;

public abstract class MarketSpreadProcess {
    protected RandomWalk randomWalk;

    public Double getLatestSpread() {
        this.randomWalk.updateRandomWalk();
        return this.randomWalk.getLatestLevel();
    }
}
