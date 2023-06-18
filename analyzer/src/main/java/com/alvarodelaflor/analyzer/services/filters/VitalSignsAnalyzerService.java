package com.alvarodelaflor.analyzer.services.filters;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.filters.vitalSigns.BradycardiaVitalSignFilter;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.signals.Signal;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VitalSignsAnalyzerService {

    private List<Filter> filters = Arrays.asList(
            new BradycardiaVitalSignFilter()
    );

    public List<CommonAlert> isAllRulesValid(Signal signal) {
        return filters.stream()
                .map(filter -> filter.isRuleValid(signal))
                .filter(filterResult -> filterResult.isPresent())
                .map(x -> x.get())
                .collect(Collectors.toList());
    }
}
