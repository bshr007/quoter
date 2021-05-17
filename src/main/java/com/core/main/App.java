package com.core.main;

import com.core.engine.QuoteProcessEngine;

import java.util.logging.Logger;

public class App
{
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main( String[] args ){

        setSimpleLogFormatter();

        int capacityOfArrayBlockingQueue = 1000;
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        QuoteProcessEngine quoteProcessEngine = new QuoteProcessEngine(capacityOfArrayBlockingQueue, availableProcessors);
        LOGGER.info("QuoteProcessEngine Initialized.");

        quoteProcessEngine.start();
        LOGGER.info("QuoteProcessEngine Started.");

        quoteProcessEngine.stop();
        LOGGER.info("QuoteProcessEngine Stopped.");

    }


    private static void setSimpleLogFormatter() {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT %4$s %2$s - %5$s%6$s%n");
    }


}
