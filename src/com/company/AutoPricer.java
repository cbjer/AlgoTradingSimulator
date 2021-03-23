package com.company;

public final class AutoPricer {
    private AutoPricer(){}

    public static void start() {
        MarketDataFeed marketDataFeed = new MarketDataFeed();
        QuoteListener quoteListener = new QuoteListener();

        Runnable pricingRun = new PricingRun(quoteListener, marketDataFeed);
        Runnable quoteRetrievalRun = new QuoteRetrievalRun(quoteListener);

        Thread pricingThread = new Thread(pricingRun);
        Thread quoteRetrievalThread = new Thread(quoteRetrievalRun);

        quoteRetrievalThread.start();
        pricingThread.start();
    }
}
