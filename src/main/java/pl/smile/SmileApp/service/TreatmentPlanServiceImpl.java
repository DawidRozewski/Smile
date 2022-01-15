package pl.smile.SmileApp.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.TreatmentPlan;

import java.time.DayOfWeek;

@Service
public class TreatmentPlanServiceImpl implements TreatmentPlanService {

    @Override
    public boolean isBookedDayIsSunday(TreatmentPlan treatmentPlan, BindingResult result) {
        if (treatmentPlan.getVisitDate().getDayOfWeek() == DayOfWeek.SUNDAY || result.hasErrors()) {
            result.rejectValue("visitDate", "error.treatment", "We are not working on Sundays :)");
            return true;
        }
        return false;
    }

}
