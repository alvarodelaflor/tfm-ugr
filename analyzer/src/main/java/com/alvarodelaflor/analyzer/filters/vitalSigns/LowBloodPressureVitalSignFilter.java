package com.alvarodelaflor.analyzer.filters.vitalSigns;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.HighBloodPressureAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.LowBloodPressureAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.util.Optional;

public class LowBloodPressureVitalSignFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal) {
        SamsungWearSignal.BloodPresure bloodPressure = signal.getSamsungWearSignals().getBloodPresure();
        return bloodPressure.getDiastolicPressure() < 60 | bloodPressure.getSystolicPressure() < 90 ? Optional.of(getCommonAlert(bloodPressure)): Optional.empty();
    }

    public CommonAlert getCommonAlert(SamsungWearSignal.BloodPresure bloodPressure) {
        return LowBloodPressureAlert.builder()
                .bloodPressure(bloodPressure)
                .build();
    }
}
