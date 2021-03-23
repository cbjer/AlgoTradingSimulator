package com.company;

import java.util.concurrent.TimeUnit;

public class PricingRun implements Runnable {
    private QuoteListener quoteListener;
    private MarketDataFeed marketDataFeed;

    PricingRun(QuoteListener quoteListener, MarketDataFeed marketDataFeed) {
        this.quoteListener = quoteListener;
        this.marketDataFeed = marketDataFeed;
    }

    public void run() {
        System.out.println("Starting pricing thread");
        try {
            while (true) {
                if (this.quoteListener.isOrderInQueue()) {
                    QuoteRequest quoteToPrice = this.quoteListener.getNextQuoteFromQueue();
                    Double quotedLevel = SwapPricing.priceQuoteRequest(this.marketDataFeed, quoteToPrice);
                    System.out.println("Quote Priced: " + quoteToPrice.toString() + ". Level: " + quotedLevel);
                }
                TimeUnit.SECONDS.sleep(2);
            }
        }
        catch (Exception e) {
            System.out.println("Caught thread issue in pricing");
        }
    }
}
