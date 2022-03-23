package pl.smile.SmileApp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.smile.SmileApp.service.impl.ServiceHelper.createDoctor;
import static pl.smile.SmileApp.service.impl.ServiceHelper.createPatient;

@DataJpaTest
class PatientRepositoryTest {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setUp() {
        doctor = createDoctor();
        patient = createPatient(doctor);
    }

    @Test
    public void givenEmail_whenGetByEmail_thenReturnPatientObject() {
        // when
        doctorRepository.save(doctor);
        patientRepository.save(patient);
        Patient patientByEmail = patientRepository.getByEmail("averoza@o2.pl");
        // then
        assertThat(patientByEmail.getEmail()).isEqualTo("averoza@o2.pl");
        assertThat(patientByEmail.getId()).isEqualTo(patient.getId());
    }

    @Test
    public void givenPesel_whenFindByPesel_thenReturnPatientObject() {
        // when
        doctorRepository.save(doctor);
        patientRepository.save(patient);
        Patient patientByPesel = patientRepository.findByPesel("87021318486");
        // then
        assertThat(patientByPesel.getPesel()).isEqualTo("87021318486");
        assertThat(patientByPesel.getId()).isEqualTo(patient.getId());
    }

    @Test
    public void givenDoctorObject_whenFindAllByDoctor_thenReturnDoctorsPatientsList() {
       // given
        doctorRepository.save(doctor);
        Patient patient1 = createPatient(doctor);
        patientRepository.save(patient);
        patientRepository.save(patient1);

        Doctor secondDoctor = createDoctor();
        secondDoctor.setFirstName("Second_Doctor");
        Patient patient2 = createPatient(secondDoctor);
        doctorRepository.save(secondDoctor);
        patientRepository.save(patient2);
        // when
        List<Patient> allPatientsByDoctor = patientRepository.findAllByDoctor(doctor);
        // then
        assertThat(allPatientsByDoctor).isNotNull();
        assertThat(allPatientsByDoctor.size()).isEqualTo(2);
        assertThat(allPatientsByDoctor.get(0).getDoctor()).isEqualTo(doctor);
        assertThat(allPatientsByDoctor.get(1).getDoctor()).isEqualTo(doctor);
    }

    @Test
    public void givenPesel_whenFindAllByPesel_thenReturnListOfPatientsByPesel() {
        // given
        doctorRepository.save(doctor);
        patientRepository.save(patient);
        // when
        List<Patient> allByPesel = patientRepository.findAllByPesel("87021318486");
        // then
        assertThat(allByPesel).isNotNull();
        assertThat(allByPesel.size()).isEqualTo(1);
    }

}