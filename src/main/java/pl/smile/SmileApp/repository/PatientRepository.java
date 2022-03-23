package pl.smile.SmileApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient getByEmail(String email);
    Patient findByPesel(String pesel);
    List<Patient> findAllByDoctor(Doctor doctor);
    List<Patient> findAllByPesel(String pesel);

}
