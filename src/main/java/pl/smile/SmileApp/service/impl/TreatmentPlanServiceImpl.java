package pl.smile.SmileApp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.TreatmentPlan;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.TreatmentPlanRepository;
import pl.smile.SmileApp.service.ErrorMessageHandling;
import pl.smile.SmileApp.service.TreatmentPlanService;

import java.time.DayOfWeek;
import java.util.List;

@AllArgsConstructor
@Service
public class TreatmentPlanServiceImpl implements TreatmentPlanService, ErrorMessageHandling {

    private final TreatmentPlanRepository treatmentPlanRepository;

    @Override
    public boolean ifSundayThrowMessage(TreatmentPlan treatmentPlan, BindingResult result) {
        if (isSunday(treatmentPlan)) {
            setErrorMessageToView(result);
            return true;
        }
        return false;
    }

    private boolean isSunday(TreatmentPlan treatmentPlan) {
        return treatmentPlan.getVisitDate().getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    @Override
    public void setErrorMessageToView(BindingResult result) {
        result.rejectValue("visitDate", "error.treatment", "We are not working on Sundays :)");
    }

    @Override
    public List<TreatmentPlan> getAllByPatientIdAndDoctor(long patientID, Doctor doctor) {
        return treatmentPlanRepository.findAllByPatientIdAndDoctor(patientID, doctor);
    }

    @Override
    public TreatmentPlan save(TreatmentPlan treatmentPlan) {
        return treatmentPlanRepository.save(treatmentPlan);
    }

    @Override
    public void delete(long id) {
        TreatmentPlan treatment_plan = getById(id);
        treatmentPlanRepository.delete(treatment_plan);
    }

    @Override
    public TreatmentPlan getById(Long id) {
        return treatmentPlanRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Treatment Plan", id));
    }

    @Override
    public List<TreatmentPlan> getAllByPatientId(long id) {
        return treatmentPlanRepository.findAllByPatientId(id);
    }
}
