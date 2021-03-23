package com.company;

import java.time.LocalDateTime;

public class QuoteRequest {
    private Integer size;
    private Integer direction;
    private LocalDateTime quoteReceiveTime;
    private static final Integer MIN_SIZE = 0;
    private static final Integer MAX_SIZE = 20000000;
    public static final Integer CLIENT_BUY_DIRECTION = 1;
    public static final Integer CLIENT_SELL_DIRECTION = -1;

    QuoteRequest(Integer size, Integer direction) {
        checkValidQuoteArguments(size, direction);
        this.size = size;
        this.direction = direction;
        this.quoteReceiveTime = LocalDateTime.now();
    }

    public Integer getSize() {
        return this.size;
    }

    public Integer getDirection() {
        return this.direction;
    }

    public LocalDateTime getQuoteReceiveTime() {
        return this.quoteReceiveTime;
    }

    @Override
    public String toString() {
        return "Quote Request. Size: " + size + ". Direction: " + direction + ". Time: " + this.quoteReceiveTime;
    }

    private void checkValidQuoteArguments(Integer size, Integer direction) {
        if (size < MIN_SIZE| size > MAX_SIZE) {
            throw new IllegalArgumentException();
        }
        if (!(direction.equals(CLIENT_BUY_DIRECTION) | direction.equals(CLIENT_SELL_DIRECTION))) {
            throw new IllegalArgumentException();
        }
    }
}
