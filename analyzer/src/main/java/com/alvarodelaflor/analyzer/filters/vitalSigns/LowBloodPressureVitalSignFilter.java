package com.alvarodelaflor.analyzer.filters.vitalSigns;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.services.ValueService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.LowBloodPressureAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.util.Optional;

public class LowBloodPressureVitalSignFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal, ValueService valueService) {
        SamsungWearSignal.BloodPresure bloodPressure = signal.getSamsungWearSignals().getBloodPresure();
        return bloodPressure.getDiastolicPressure() < valueService.getLDiastolicPressure() | bloodPressure.getSystolicPressure() < valueService.getLSystolicPressure() ? Optional.of(getCommonAlert(bloodPressure)): Optional.empty();
    }

    public CommonAlert getCommonAlert(SamsungWearSignal.BloodPresure bloodPressure) {
        return LowBloodPressureAlert.builder()
                .bloodPressure(bloodPressure)
                .build();
    }
}
