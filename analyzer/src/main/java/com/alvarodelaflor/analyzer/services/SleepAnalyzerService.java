package com.alvarodelaflor.analyzer.services;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.filters.sleep.RemSleepFilter;
import com.alvarodelaflor.domain.model.signals.Signal;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SleepAnalyzerService {

    private List<Filter> filters = Arrays.asList(new RemSleepFilter());

    public Map<String, Double> isAllRulesValid(Signal signal) {
        return filters.stream()
                .filter(rule -> !rule.isRuleValid(signal))
                .collect(Collectors.toMap(Filter::getName, Filter::getWeight));
    }
}
