package com.alvarodelaflor.analyzer.filters;

import com.alvarodelaflor.domain.model.alerts.SleepCommonAlert;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.util.Optional;

public interface Filter {

    Optional<SleepCommonAlert> isRuleValid(Signal signal);
}
