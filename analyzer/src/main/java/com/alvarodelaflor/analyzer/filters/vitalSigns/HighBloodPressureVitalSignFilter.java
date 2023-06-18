package com.alvarodelaflor.analyzer.filters.vitalSigns;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.HighBloodPressureAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.util.Optional;

public class HighBloodPressureVitalSignFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal) {
        SamsungWearSignal.BloodPresure bloodPressure = signal.getSamsungWearSignals().getBloodPresure();
        return bloodPressure.getDiastolicPressure() < 0 & bloodPressure.getSystolicPressure() < 0 ? Optional.empty() : Optional.of(getCommonAlert(bloodPressure));
    }

    public CommonAlert getCommonAlert(SamsungWearSignal.BloodPresure bloodPressure) {
        return HighBloodPressureAlert.builder()
                .bloodPressure(bloodPressure)
                .build();
    }
}
