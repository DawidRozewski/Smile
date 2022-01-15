package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.TreatmentPlan;

public interface TreatmentPlanService {
    boolean isBookedDayIsSunday(TreatmentPlan treatmentPlan, BindingResult result);
}
