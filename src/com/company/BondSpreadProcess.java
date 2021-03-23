package com.company;

public class BondSpreadProcess extends MarketSpreadProcess{
    BondSpreadProcess() {
        this.randomWalk = new RandomWalk(3.0, 0.0, 0.001, -0.001);
    }
}
