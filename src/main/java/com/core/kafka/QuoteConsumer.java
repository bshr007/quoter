package com.core.kafka;

import com.core.db.QuotestoreDatabse;
import com.core.model.Quote;
import com.core.quote.Processor;
import com.core.quote.QuoteProcessorFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class QuoteConsumer implements Runnable{

    private static final Logger LOGGER = Logger.getLogger(QuoteConsumer.class.getName());

    private QuotestoreDatabse quotestoreDatabse = new QuotestoreDatabse();

    private BlockingQueue<Quote> quoteBlockingQueue;
    private AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    private QuoteProcessorFactory quoteProcessorFactory;

    public QuoteConsumer(BlockingQueue<Quote> quoteBlockingQueue, QuoteProcessorFactory quoteProcessorFactory) {
        this.quoteBlockingQueue = quoteBlockingQueue;
        this.quoteProcessorFactory = quoteProcessorFactory;
    }

    @Override
    public void run() {
        while (atomicBoolean.get()) {
            try {
                Quote quote = quoteBlockingQueue.take();
                Processor processor = quoteProcessorFactory.getProcessor(quote.getQuoteType());
                Quote process = processor.process(quote);
                LOGGER.info(String.format("KAFKA : Quote Id [%s], Type [%s], Initial Price [%s], Quoting Price [%s]",
                        quote.getId(), quote.getQuoteType(), quote.getInitialPrice(), process.getQuotingPrice()));
                quotestoreDatabse.save(quote);
            } catch (Exception e) {
                LOGGER.severe("Exception occurred while running quote processor engine" + e.getLocalizedMessage());
            }
        }
    }

    public void stop(){
        atomicBoolean.getAndSet(false);
    }

}
