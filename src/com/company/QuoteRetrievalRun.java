package com.company;

import java.util.concurrent.TimeUnit;

public class QuoteRetrievalRun implements Runnable {
    private QuoteListener quoteListener;
    private SimpleLogger quoteRequestLogger;

    QuoteRetrievalRun(QuoteListener quoteListener) {
        this.quoteListener = quoteListener;
    }

    public void run() {
        try {
            while (true) {
                this.quoteListener.checkForNewQuotes();
                TimeUnit.MILLISECONDS.sleep(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Caught thread issue in quote retrieval");
        }
    }
}
