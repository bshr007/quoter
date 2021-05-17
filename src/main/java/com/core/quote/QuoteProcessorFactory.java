package com.core.quote;

public class QuoteProcessorFactory {

    public Processor getProcessor(final QuoteType quoteType){
        switch (quoteType){
            case RFQ:
                return new RfqProcessor();
            case CONT:
                return new ContProcessor();
            case VAL:
                return new ValProcessor();
            default:
                throw new RuntimeException("Invalid QuoteType.");
        }
    }
}
