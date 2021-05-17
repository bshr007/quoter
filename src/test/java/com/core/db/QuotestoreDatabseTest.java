package com.core.db;

import com.core.model.Quote;
import com.core.quote.QuoteBuilder;
import com.core.quote.QuoteType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.core.quote.QuoteBuilder.quoteBuilder;
import static org.junit.jupiter.api.Assertions.*;

class QuotestoreDatabseTest {

    private QuotestoreDatabse quotestoreDatabse = new QuotestoreDatabse();

    @Test
    void saveQuoteInDatabase() {

        //Given
        Quote quote = quoteBuilder()
                .withQuoteId(UUID.randomUUID())
                .withQuoteType(QuoteType.RFQ)
                .withInitialPrice(1).build();

        //When
        quotestoreDatabse.save(quote);
        Quote actualQuote = quotestoreDatabse.getQuote(quote.getId());

        //then
        assertEquals(quote, actualQuote);

    }
}