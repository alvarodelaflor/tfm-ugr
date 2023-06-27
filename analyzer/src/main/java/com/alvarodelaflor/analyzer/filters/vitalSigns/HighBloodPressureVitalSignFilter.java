package com.alvarodelaflor.analyzer.filters.vitalSigns;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.services.ValueService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.HighBloodPressureAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.util.Optional;

public class HighBloodPressureVitalSignFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal, ValueService valueService) {
        Optional<CommonAlert> res;
        if (signal.getSamsungWearSignals() != null) {
            SamsungWearSignal.BloodPresure bloodPressure = signal.getSamsungWearSignals().getBloodPresure();
            res =  bloodPressure.getDiastolicPressure() < valueService.getHDiastolicPressure() & bloodPressure.getSystolicPressure() < valueService.getHDiastolicPressure() ? Optional.empty() : Optional.of(getCommonAlert(bloodPressure));
        } else {
            res = Optional.empty();
        }
        return res;
    }

    public CommonAlert getCommonAlert(SamsungWearSignal.BloodPresure bloodPressure) {
        return HighBloodPressureAlert.builder()
                .bloodPressure(bloodPressure)
                .build();
    }
}
