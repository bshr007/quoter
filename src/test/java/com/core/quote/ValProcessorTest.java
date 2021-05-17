package com.core.quote;

import com.core.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.UUID;

import static com.core.quote.QuoteBuilder.quoteBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ValProcessorTest {

    @Spy
    private ValProcessor valProcessor ;

    double quotingPrice = 21.45;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(valProcessor.getPrice()).thenReturn(quotingPrice);
    }


    @Test
    void process() throws Exception {

        //Given
        QuoteBuilder quote = quoteBuilder()
                .withQuoteId(UUID.randomUUID())
                .withQuoteType(QuoteType.RFQ)
                .withInitialPrice(1);

        //When
        Quote process = valProcessor.process(quote.build());

        //Then
        assertEquals(process.getQuotingPrice(), quotingPrice);
    }
}