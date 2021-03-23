package com.company;

public class QuoteReply {
    private Double priceResponse;
    private QuoteRequest quoteRequest;
    private Double midPrice;
    private Double bidOffer;

    QuoteReply(QuoteRequest quoteRequest, Double priceResponse, Double midPrice, Double bidOffer) {
        this.quoteRequest = quoteRequest;
        this.priceResponse = priceResponse;
        this.midPrice = midPrice;
        this.bidOffer = bidOffer;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("PriceShown: %1.7f", this.priceResponse));
        stringBuilder.append(String.format(" Mid: %1.7f", this.midPrice));
        stringBuilder.append(String.format(" BO: %1.7f", this.bidOffer));
        stringBuilder.append(" | ");
        stringBuilder.append(this.quoteRequest.toString());
        return stringBuilder.toString();
    }
}
