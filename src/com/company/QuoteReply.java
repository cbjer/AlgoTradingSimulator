package com.company;

public class QuoteReply {
    private Double priceResponse;
    private QuoteRequest quoteRequest;
    private Double midPrice;

    QuoteReply(QuoteRequest quoteRequest, Double priceResponse, Double midPrice) {
        this.quoteRequest = quoteRequest;
        this.priceResponse = priceResponse;
        this.midPrice = midPrice;
    }
}
