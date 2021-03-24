package com.company;

public class AssetSwapSpreadProcess extends MarketSpreadProcess{
    private static final Double ASW_MEAN_REVERSION_FACTOR = 5.0;
    private static final Double ASW_DRIFT = 0.0;
    private static final Double ASW_SIGMA = 0.0003;
    private static final Double ASW_INITIAL_VALUE = 0.0035;

    AssetSwapSpreadProcess() {
        this.randomWalk = new RandomWalk(ASW_MEAN_REVERSION_FACTOR, ASW_DRIFT, ASW_SIGMA, ASW_INITIAL_VALUE);
    }
}
