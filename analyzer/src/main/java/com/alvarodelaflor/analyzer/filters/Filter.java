package com.alvarodelaflor.analyzer.filters;

import com.alvarodelaflor.domain.model.signals.Signal;

public interface Filter {

    Boolean isRuleValid(Signal signal);

    String getName();

    Double getWeight();
}
