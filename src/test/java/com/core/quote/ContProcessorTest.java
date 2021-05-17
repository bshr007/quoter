package com.core.quote;

import com.core.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.UUID;

import static com.core.quote.QuoteBuilder.quoteBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ContProcessorTest {

    @Spy
    private ContProcessor contProcessor ;

    double quotingPrice = 22.45;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        Optional<Method> method = ReflectionUtils.findMethod(RfqProcessor.class, "getPrice");
        ReflectionUtils.makeAccessible(method.get());
        when(contProcessor.getPrice()).thenReturn(quotingPrice);
    }


    @Test
    void process() throws Exception {

        //Given
        QuoteBuilder quote = quoteBuilder()
                .withQuoteId(UUID.randomUUID())
                .withQuoteType(QuoteType.RFQ)
                .withInitialPrice(1);

        //When
        Quote process = contProcessor.process(quote.build());

        //Then
        assertEquals(process.getQuotingPrice(), quotingPrice);
    }
}