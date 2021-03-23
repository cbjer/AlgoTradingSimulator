package com.company;

public class AssetSwapSpreadProcess extends MarketSpreadProcess{
    AssetSwapSpreadProcess() {
        this.randomWalk = new RandomWalk(5.0, 0.0, 0.0003, 0.0035);
    }
}
