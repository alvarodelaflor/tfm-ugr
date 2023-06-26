package com.alvarodelaflor.analyzer.services.filters;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.filters.sleep.AwakeningsSleepFilter;
import com.alvarodelaflor.analyzer.filters.sleep.DayTimeNapsSleepFilter;
import com.alvarodelaflor.analyzer.filters.sleep.RemSleepFilter;
import com.alvarodelaflor.analyzer.filters.sleep.WakingUpEarlySleepFilter;
import com.alvarodelaflor.analyzer.services.ValueService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.signals.Signal;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SleepAnalyzerService {

    private List<Filter> filters = Arrays.asList(
            new RemSleepFilter(),
            new AwakeningsSleepFilter(),
            new DayTimeNapsSleepFilter(),
            new WakingUpEarlySleepFilter()
    );

    public List<CommonAlert> isAllRulesValid(Signal signal, ValueService valueService) {
        return filters.stream()
                .map(filter -> filter.isRuleValid(signal, valueService))
                .filter(filterResult -> filterResult.isPresent())
                .map(x -> x.get())
                .collect(Collectors.toList());
    }
}
