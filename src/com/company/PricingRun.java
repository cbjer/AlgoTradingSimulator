package com.company;

import java.util.concurrent.TimeUnit;

public class PricingRun implements Runnable {
    private QuoteListener quoteListener;
    private MarketDataFeed marketDataFeed;
    private SimpleLogger pricingLogger;

    PricingRun(QuoteListener quoteListener, MarketDataFeed marketDataFeed) {
        this.quoteListener = quoteListener;
        this.marketDataFeed = marketDataFeed;
        this.pricingLogger = new SimpleLogger("PricingLog");
    }

    public void run() {
        try {
            while (true) {
                if (this.quoteListener.isOrderInQueue()) {
                    QuoteRequest quoteToPrice = this.quoteListener.getNextQuoteFromQueue();
                    QuoteReply pricedQuote = SwapPricing.priceQuoteRequest(this.marketDataFeed, quoteToPrice);
                    this.pricingLogger.log(pricedQuote.toString());
                }
                TimeUnit.SECONDS.sleep(2); //Simulating pricing taking longer -> Quotes buildup to price
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
