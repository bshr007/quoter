package com.core.model;

import com.core.quote.QuoteType;

import java.util.Objects;
import java.util.UUID;

public class Quote {
    private UUID id;
    private QuoteType quoteType;
    private double initialPrice;
    private double quotingPrice;

    public Quote(UUID id, QuoteType quoteType, double initialPrice) {
        this.id = id;
        this.quoteType = quoteType;
        this.initialPrice = initialPrice;
    }

    public Quote() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public QuoteType getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getQuotingPrice() {
        return quotingPrice;
    }

    public void setQuotingPrice(double quotingPrice) {
        this.quotingPrice = quotingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Double.compare(quote.initialPrice, initialPrice) == 0 && Double.compare(quote.quotingPrice, quotingPrice) == 0 && Objects.equals(id, quote.id) && quoteType == quote.quoteType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quoteType, initialPrice, quotingPrice);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", quoteType=" + quoteType +
                ", initialPrice=" + initialPrice +
                ", quotingPrice=" + quotingPrice +
                '}';
    }
}
