package com.company;

import java.util.concurrent.TimeUnit;

public class QuoteRetrievalRun implements Runnable {
    private QuoteListener quoteListener;

    QuoteRetrievalRun(QuoteListener quoteListener) {
        this.quoteListener = quoteListener;
    }

    public void run() {
        System.out.println("Starting quoteRetrieval thread");
        int loops = 0;
        try {
            while (true) {
                this.quoteListener.checkForNewQuotes();
                TimeUnit.MILLISECONDS.sleep(1);
            }
        }
        catch (Exception e) {
            System.out.println("Caught thread issue in quote retrieval");
        }
    }
}
