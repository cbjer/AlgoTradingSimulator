package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
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