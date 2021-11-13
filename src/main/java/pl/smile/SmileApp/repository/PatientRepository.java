package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
