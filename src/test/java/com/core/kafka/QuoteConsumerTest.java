package com.core.kafka;

import com.core.model.Quote;
import com.core.quote.QuoteProcessorFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


class QuoteConsumerTest {

    @Mock
    private BlockingQueue<Quote> quoteBlockingQueue;

    @Mock
    private AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    @Mock
    private QuoteProcessorFactory quoteProcessorFactory;

    @Test
    public void testCounterWithConcurrency() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        QuoteConsumer quoteConsumer = new QuoteConsumer(quoteBlockingQueue, quoteProcessorFactory);

        //TODO
    }

}