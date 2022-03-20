package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.TreatmentPlan;

import java.util.List;

public interface TreatmentPlanService {
    boolean ifSundayThrowMessage(TreatmentPlan treatmentPlan, BindingResult result);
    TreatmentPlan save(TreatmentPlan treatmentPlan);
    void delete(long id);
    TreatmentPlan getById(Long id);
    List<TreatmentPlan> getAllByPatientIdAndDoctor(long patientID, Doctor doctor);
    List<TreatmentPlan> getAllByPatientId(long id);
}
