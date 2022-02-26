package pl.smile.SmileApp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.TreatmentPlan;
import pl.smile.SmileApp.service.TreatmentPlanService;

import java.time.DayOfWeek;

@Service
public class TreatmentPlanServiceImpl implements TreatmentPlanService {

    @Override
    public boolean ifSundayThrowMessage(TreatmentPlan treatmentPlan, BindingResult result) {
        if (isSunday(treatmentPlan)) {
            result.rejectValue("visitDate", "error.treatment", "We are not working on Sundays :)");
            return true;
        }
        return false;
    }

    private boolean isSunday(TreatmentPlan treatmentPlan) {
        return treatmentPlan.getVisitDate().getDayOfWeek() == DayOfWeek.SUNDAY;
    }

}
