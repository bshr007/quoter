package com.core.quote;

import com.core.model.Quote;

import java.security.SecureRandom;
import java.util.logging.Logger;

public class RfqProcessor implements Processor{

    private static final Logger LOGGER = Logger.getLogger(RfqProcessor.class.getName());

    private SecureRandom secureRandom = new SecureRandom();

    @Override
    public Quote process(Quote quote) {
        double price = getPrice();
        quote.setQuotingPrice(price);
        LOGGER.info(quote.toString());
        return quote;
    }

    public double getPrice() {
        double price = secureRandom.nextDouble();
        return price;
    }
}
