package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.TreatmentPlan;

import java.util.List;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {
    List<TreatmentPlan> findAllByPatientIdAndDoctor(long patientId, Doctor doctor);
    List<TreatmentPlan> findAllByPatientId(long patientId);

}
