package com.company;

public class BondSpreadProcess extends MarketSpreadProcess{
    private static final Double BOND_MEAN_REVERSION_FACTOR = 3.0;
    private static final Double BOND_DRIFT = 0.0;
    private static final Double BOND_SIGMA = 0.001;
    private static final Double BOND_INITIAL_VALUE = -0.001;

    BondSpreadProcess() {
        this.randomWalk = new RandomWalk(BOND_MEAN_REVERSION_FACTOR, BOND_DRIFT, BOND_SIGMA, BOND_INITIAL_VALUE);
    }
}
