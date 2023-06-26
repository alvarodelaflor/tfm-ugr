package com.alvarodelaflor.analyzer.filters;

import com.alvarodelaflor.analyzer.services.ValueService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.util.Optional;

public interface Filter {

    Optional<CommonAlert> isRuleValid(Signal signal, ValueService valueService);
}
