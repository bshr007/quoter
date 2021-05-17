package com.core.service;

import com.core.quote.QuoteType;

import java.util.SplittableRandom;

public class QuotingService {

    private static int min = 1;
    private static int max = 100;

    private static SplittableRandom splittableRandom = new SplittableRandom();
    private static QuoteType[] quoteTypes = QuoteType.values();

    public static double getRandomPrice(){
        return splittableRandom.nextDouble(max - min) + min;
    }

    public static QuoteType getRandomQuoteType(){
        return quoteTypes[splittableRandom.nextInt(quoteTypes.length)];
    }
}
