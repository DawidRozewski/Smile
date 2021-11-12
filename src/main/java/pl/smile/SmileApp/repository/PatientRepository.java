package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smile.SmileApp.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
