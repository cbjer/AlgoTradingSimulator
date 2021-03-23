package com.company;

import java.time.LocalDateTime;

public class QuoteRequest {
    private Integer size;
    private Integer direction;
    private LocalDateTime quoteReceiveTime;

    public static final Integer CLIENT_BUY_DIRECTION = 1;
    public static final Integer CLIENT_SELL_DIRECTION = -1;
    private static final Integer MIN_SIZE = 0;
    private static final Integer MAX_SIZE = 1000000000;
    private static final Character CLIENT_BUY_STRING = 'B';
    private static final Character CLIENT_SELL_STRING = 'S';

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
        return "RFQ. Size: " + this.size + " Dir: " + getDirectionSymbol() + ". Time: " + this.quoteReceiveTime.toString();
    }

    private Character getDirectionSymbol() {
        if (this.direction.equals(CLIENT_BUY_DIRECTION))
            return CLIENT_BUY_STRING;
        else
            return CLIENT_SELL_STRING;
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
