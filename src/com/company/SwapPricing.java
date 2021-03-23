package com.company;

public final class SwapPricing {
    private static final Double ROUNDING_FACTOR = 100000000.0;
    private static final Double BID_OFFER_ADJUSTMENT = 0.2 / 10000.0;
    private static final Double MIN_SIZE_ADJ = 1.0;
    private static final Double MAX_SIZE_ADJ = 3.0;
    private static final Double MIN_INTERPOLATE_SIZE = 100000.0;
    private static final Double MAX_INTERPOLATE_SIZE = 10000000.0;

    private SwapPricing(){}

    public static QuoteReply priceQuoteRequest(MarketDataFeed marketDataFeed, QuoteRequest quoteRequest) {
        Double swapMid = getSwapSpreadEstimate(marketDataFeed);
        return priceQuoteRequest(swapMid, quoteRequest);
    }

    public static QuoteReply priceQuoteRequest(Double swapMid, QuoteRequest quoteRequest) {
        Double bidOfferAdjustment = getBidOfferAdjustment(quoteRequest);
        Double roundedPrice = roundPrice(swapMid + bidOfferAdjustment);
        return new QuoteReply(quoteRequest, roundedPrice, swapMid, bidOfferAdjustment);
    }

    private static Double getSwapSpreadEstimate(Double bondSpread, Double assetSwapSpread) {
        return bondSpread + assetSwapSpread;
    }

    private static Double getSwapSpreadEstimate(MarketDataFeed marketDataFeed) {
        return getSwapSpreadEstimate(marketDataFeed.getBondSpread(), marketDataFeed.getAssetSwapSpread());
    }

    private static Double roundPrice(Double price) {
        price = price * ROUNDING_FACTOR;
        price = (double) Math.round(price);
        return price / ROUNDING_FACTOR;
    }

    private static Double getBidOfferAdjustment(QuoteRequest quoteRequest) {
        // direction == 1 -> clientBuys -> offerSide -> addBidOffer
        Double directionFactor = getDirectionFactor(quoteRequest);
        Double sizeFactor = getSizeAdjustment(quoteRequest);
        return BID_OFFER_ADJUSTMENT * directionFactor * sizeFactor;
    }

    private static Double getDirectionFactor(QuoteRequest quoteRequest) {
        Integer direction = quoteRequest.getDirection();
        if (direction.equals(QuoteRequest.CLIENT_BUY_DIRECTION)) {
            return (double) QuoteRequest.CLIENT_BUY_DIRECTION;
        }
        else {
            return (double) QuoteRequest.CLIENT_SELL_DIRECTION;
        }
    }

    private static Double getSizeAdjustment(QuoteRequest quoteRequest) {
        Integer size = quoteRequest.getSize();
        Double adjustmentFactor = interpolateAdjustmentValue(size);
        return Math.max(Math.min(MAX_SIZE_ADJ, adjustmentFactor), MIN_SIZE_ADJ);
    }

    private static Double interpolateAdjustmentValue(Integer size) {
        Double slope = (MAX_SIZE_ADJ - MIN_SIZE_ADJ) / (MAX_INTERPOLATE_SIZE - MIN_INTERPOLATE_SIZE);
        return slope * (size - MIN_INTERPOLATE_SIZE) + MIN_SIZE_ADJ;
    }
}
