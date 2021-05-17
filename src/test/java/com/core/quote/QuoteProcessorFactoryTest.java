package com.core.quote;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("QuoteProcessor")
class QuoteProcessorFactoryTest {

    private final QuoteProcessorFactory quoteProcessorFactory = new QuoteProcessorFactory();

    @Test
    @DisplayName("Throws NullPointerException when passed null object")
    void shouldThrowNullPointerExceptionWhenPassNullObjectToQuoteProcessorFactory() {
        assertThrows(NullPointerException.class, () -> {
            quoteProcessorFactory.getProcessor(null);
        });
    }

    @ParameterizedTest
    @DisplayName("Throws RuntimeException when passed incorrect quote request type object")
    @ValueSource(strings = {"ABC", "DEF", "GHI"})
    void shouldThrowRuntimeExceptionWhenPassInvalidQuoteRequestType(final String quoteRequestType) {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            quoteProcessorFactory.getProcessor(QuoteType.valueOf(quoteRequestType));
        });
        String expectedErrorMessage = "No enum constant com.core.quote.QuoteType."+quoteRequestType;
        String actualErrorMessage = runtimeException.getMessage();
        assertTrue(expectedErrorMessage.contains(actualErrorMessage));
    }

}