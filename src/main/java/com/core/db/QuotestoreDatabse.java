package com.core.db;

import com.core.model.Quote;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class QuotestoreDatabse {

    private static final Logger LOGGER = Logger.getLogger(QuotestoreDatabse.class.getName());

    private Map<UUID, Quote> quoteStoreDB = new ConcurrentHashMap<>();

    public void save(Quote quote) {
        quoteStoreDB.put(quote.getId(), quote);
        LOGGER.info("Quote saved in DATABASE : "+quote);
    }

    public Quote getQuote(final UUID uuid){
        if(quoteStoreDB.containsKey(uuid)){
            Quote quote = quoteStoreDB.get(uuid);
            LOGGER.info("Quote present in database : "+quote);
            return quote;
        }
        throw new RuntimeException(String.format("Quote [%s] not persist in database,", uuid));
    }

    public Map<UUID, Quote> findAll(){
        if(!quoteStoreDB.isEmpty()){
            return quoteStoreDB;
        }
        throw new RuntimeException("Database is empty!");
    }

    public void delete(final Quote quote){
        if(!quoteStoreDB.isEmpty()){
            if(quoteStoreDB.containsKey(quote.getId())){
                quoteStoreDB.remove(quote.getId());
                LOGGER.info("Deleted quote : "+quote);
            }else{
                throw new RuntimeException(String.format("Quote [%s] not persist in database,", quote));
            }
        }
        throw new RuntimeException("Database is empty!");
    }
}
