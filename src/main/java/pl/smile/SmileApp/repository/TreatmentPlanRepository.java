package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.TreatmentPlan;

import java.util.List;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {
    List<TreatmentPlan> findAllByPatientIdAndDoctor(long id, Doctor doctor);
    List<TreatmentPlan> findAllByPatientId(long id);

}
