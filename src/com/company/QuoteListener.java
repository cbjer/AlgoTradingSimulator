package com.company;

import java.util.concurrent.ArrayBlockingQueue;

public class QuoteListener {
    private ArrayBlockingQueue<QuoteRequest> quoteQueue;
    private QuoteGenerator quoteGenerator;
    private static final Integer MAX_QUEUE_SIZE = 10000000;

    QuoteListener() {
        this.quoteQueue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);
        this.quoteGenerator = new QuoteGenerator();
    }

    public Boolean isOrderInQueue() {
        return !this.quoteQueue.isEmpty();
    }

    public QuoteRequest getNextQuoteFromQueue() {
        if (this.quoteQueue.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.quoteQueue.remove();
    }

    public void checkForNewQuotes() {
        if (this.quoteGenerator.hasOrderArrived()) {
            QuoteRequest newQuote = this.quoteGenerator.generateQuoteRequest();
            this.quoteQueue.add(newQuote);
            System.out.println("New Quote Added! Remaining Quotes in Queue: " + numberQuotesStillInQueue());
        }
    }

    public int numberQuotesStillInQueue() {
        return this.quoteQueue.size();
    }
}
