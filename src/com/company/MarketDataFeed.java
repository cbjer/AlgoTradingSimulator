package com.company;

public class MarketDataFeed {
    private BondSpreadProcess bondSpreadProcess;
    private AssetSwapSpreadProcess assetSwapSpreadProcess;

    MarketDataFeed() {
        this.bondSpreadProcess = new BondSpreadProcess();
        this.assetSwapSpreadProcess = new AssetSwapSpreadProcess();
    }

    public Double getBondSpread() {
        return this.bondSpreadProcess.getLatestSpread();
    }

    public Double getAssetSwapSpread() {
        return this.assetSwapSpreadProcess.getLatestSpread();
    }
}
