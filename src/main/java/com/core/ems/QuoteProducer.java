package com.core.ems;

import com.core.model.Quote;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import static com.core.service.QuotingService.getRandomPrice;
import static com.core.service.QuotingService.getRandomQuoteType;

public class QuoteProducer implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(QuoteProducer.class.getName());

    private int size;
    private BlockingQueue<Quote> quoteBlockingQueue;
    private AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    public QuoteProducer(BlockingQueue<Quote> quoteBlockingQueue, int size) {
        this.quoteBlockingQueue = quoteBlockingQueue;
        this.size = size;
    }

    @Override
    public void run() {
        while (atomicBoolean.get()){
            getQuotesFromEms();
        }
    }

    public void getQuotesFromEms() {
        int min = 1;
        int max = 50;
        Quote quote = new Quote(UUID.randomUUID(), getRandomQuoteType(), getRandomPrice());
        LOGGER.info(String.format("EMS : Quote Id [%s], Type [%s], Initial Price [%f], Quoting Price [%f]",
                quote.getId(), quote.getQuoteType(), quote.getInitialPrice(), quote.getQuotingPrice()));
        try {
            quoteBlockingQueue.put(quote);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      /* IntStream.range(0, this.size).boxed().forEach( number ->{
           try {

           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       });*/
    }

    public void stop(){
        atomicBoolean.getAndSet(false);
    }
}
