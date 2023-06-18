package com.alvarodelaflor.analyzer.services;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.filters.sleep.AwakeningsSleepFilter;
import com.alvarodelaflor.analyzer.filters.sleep.DayTimeNapsSleepFilter;
import com.alvarodelaflor.analyzer.filters.sleep.RemSleepFilter;
import com.alvarodelaflor.analyzer.filters.sleep.WakingUpEarlySleepFilter;
import com.alvarodelaflor.domain.model.alerts.SleepCommonAlert;
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

    public List<SleepCommonAlert> isAllRulesValid(Signal signal) {
        return filters.stream()
                .map(filter -> filter.isRuleValid(signal))
                .filter(filterResult -> filterResult.isPresent())
                .map(x -> x.get())
                .collect(Collectors.toList());
    }
}
