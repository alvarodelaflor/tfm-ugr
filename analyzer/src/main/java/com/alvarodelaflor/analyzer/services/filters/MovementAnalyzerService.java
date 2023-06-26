package com.alvarodelaflor.analyzer.services.filters;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.filters.movement.DisableSensorsMovementFilter;
import com.alvarodelaflor.analyzer.filters.movement.RepeatedActionMovementFilter;
import com.alvarodelaflor.analyzer.services.ValueService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.signals.Signal;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementAnalyzerService {

    private List<Filter> filters = Arrays.asList(
            new RepeatedActionMovementFilter(),
            new DisableSensorsMovementFilter()
    );

    public List<CommonAlert> isAllRulesValid(Signal signal, ValueService valueService) {
        return filters.stream()
                .map(filter -> filter.isRuleValid(signal, valueService))
                .filter(filterResult -> filterResult.isPresent())
                .map(x -> x.get())
                .collect(Collectors.toList());
    }
}
