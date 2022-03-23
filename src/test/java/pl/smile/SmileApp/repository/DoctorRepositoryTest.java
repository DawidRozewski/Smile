package pl.smile.SmileApp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.smile.SmileApp.entity.Doctor;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.smile.SmileApp.service.impl.ServiceHelper.createDoctor;

@DataJpaTest
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;
    private Doctor doctor;

    @Test
    public void givenEmail_whenGetByEmail_thenReturnDoctorObject() {
        // given
         doctor = createDoctor();
         String email = doctor.getEmail();
         doctorRepository.save(doctor);
        // when
        Doctor savedDoctor = doctorRepository.getByEmail(email);
        // then
        assertThat(savedDoctor).isNotNull();
        assertThat(savedDoctor.getId()).isEqualTo(doctor.getId());
    }
}