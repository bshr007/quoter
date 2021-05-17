package com.core.quote;

import com.core.model.Quote;

import java.util.UUID;

public class QuoteBuilder {

    private UUID id;
    private QuoteType quoteType;
    private double initialPrice;
    private double quotingPrice;

    public QuoteBuilder() {
    }

    public static QuoteBuilder quoteBuilder(){
        return new QuoteBuilder();
    }

    public QuoteBuilder withQuoteId(UUID id){
        this.id = id;
        return this;
    }

    public QuoteBuilder withQuoteType(QuoteType quoteType){
        this.quoteType = quoteType;
        return this;
    }

    public QuoteBuilder withInitialPrice(double initialPrice){
        this.initialPrice = initialPrice;
        return this;
    }

    public QuoteBuilder withQuotingPrice(double quotingPrice){
        this.quotingPrice = quotingPrice;
        return this;
    }

    public Quote build(){
        Quote quote = new Quote();
        quote.setId(id);
        quote.setQuoteType(quoteType);
        quote.setInitialPrice(initialPrice);
        quote.setQuotingPrice(quotingPrice);
        return quote;
    }
}
