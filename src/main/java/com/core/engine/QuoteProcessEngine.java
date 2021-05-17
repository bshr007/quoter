package com.core.engine;

import com.core.ems.QuoteProducer;
import com.core.kafka.QuoteConsumer;
import com.core.model.Quote;
import com.core.quote.QuoteProcessorFactory;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class QuoteProcessEngine {

    private static final Logger LOGGER = Logger.getLogger(QuoteProcessEngine.class.getName());

    private QuoteProducer quoteProducer;
    private QuoteConsumer quoteConsumer;
    private BlockingQueue<Quote> queue;
    private QuoteProcessorFactory quoteProcessorFactory ;
    private int quoteSize;
    private int availableProcessors;
    private ExecutorService executorService;

    public QuoteProcessEngine(final int quoteSize, final int availableProcessors) {
        this.quoteSize = quoteSize;
        this.availableProcessors = availableProcessors;
        this.queue = new ArrayBlockingQueue<>(quoteSize);
        quoteProcessorFactory = new QuoteProcessorFactory();
        quoteProducer = new QuoteProducer(queue, quoteSize);
        quoteConsumer = new QuoteConsumer(queue, quoteProcessorFactory);
    }


    public void start() {
        executorService = Executors.newFixedThreadPool(availableProcessors);
        LOGGER.info(String.format("Created a thread pool with [%s]] threads", availableProcessors));

        for(int i=0; i<availableProcessors/2; i++){
            executorService.execute(quoteProducer);
            executorService.execute(quoteConsumer);
            LOGGER.info(String.format("Tasks [%s] added.", i));
        }

        LOGGER.info("Quoting engine process started - ");

       // runQuoteEngineForXSeconds(quoteProducer, quoteConsumer, 1);
        shutdownExecutorService(executorService);
        processPendingMessages(queue);
    }

    private void processPendingMessages(BlockingQueue<Quote> queue) {
        if(!queue.isEmpty()){
            LOGGER.info("Message Pending -  "+queue.size());
        }
    }

    private static void runQuoteEngineForXSeconds(QuoteProducer quoteProducer, QuoteConsumer quoteConsumer, int time) {
        Runnable shutdown = () ->{
            try {
                TimeUnit.SECONDS.sleep(time);
                LOGGER.info("Quoting engine process terminated ");
                quoteProducer.stop();
                TimeUnit.SECONDS.sleep(time/2);
                quoteConsumer.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        shutdown.run();
    }

    private static void shutdownExecutorService(ExecutorService executorService) {
        executorService.shutdown();
        LOGGER.info("Quoting engine process stopped");

        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                LOGGER.info("Quoting engine process forcefully stopped");

            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            LOGGER.info("Quoting engine process forcefully shutdown now");
        }
    }

    public void stop() {
        shutdownExecutorService(executorService);
    }
}
