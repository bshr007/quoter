package com.core.quote;

import com.core.model.Quote;

public interface Processor {
    Quote process(Quote quote);
}
