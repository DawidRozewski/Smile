package pl.smile.SmileApp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.entity.TreatmentPlan;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.smile.SmileApp.service.impl.ServiceHelper.*;

@DataJpaTest
class TreatmentPlanRepositoryTest {

    @Autowired
    private TreatmentPlanRepository treatmentPlanRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setUp() {
        doctor = createDoctor();
        doctorRepository.save(doctor);

        patient = createPatient(doctor);
        patientRepository.save(patient);
    }

    @Test
    public void givenPatientIdAndDoctorObject_whenFindAllByPatientIdAndDoctor_thenReturnTreatmentPlanList() {
        // given
        TreatmentPlan treatmentPlan = createTreatmentPlan(doctor, patient, "description");
        TreatmentPlan treatmentPlan1 = createTreatmentPlan(doctor, patient, "description1");
        treatmentPlanRepository.save(treatmentPlan);
        treatmentPlanRepository.save(treatmentPlan1);

        Patient secondPatient = createPatient(doctor);
        TreatmentPlan otherPatientPlan = createTreatmentPlan(doctor, secondPatient, "example");
        patientRepository.save(secondPatient);
        treatmentPlanRepository.save(otherPatientPlan);
        // when
        List<TreatmentPlan> allByPatientIdAndDoctor = treatmentPlanRepository.findAllByPatientIdAndDoctor(patient.getId(), doctor);
        // then
        assertThat(allByPatientIdAndDoctor).isNotNull();
        assertThat(allByPatientIdAndDoctor.size()).isEqualTo(2);
        assertThat(allByPatientIdAndDoctor.get(0).getPatient()).isEqualTo(patient);
        assertThat(allByPatientIdAndDoctor.get(1).getPatient()).isEqualTo(patient);
    }

}