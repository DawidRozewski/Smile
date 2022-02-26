package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.TreatmentPlan;

public interface TreatmentPlanService {
    boolean ifSundayThrowMessage(TreatmentPlan treatmentPlan, BindingResult result);
}
