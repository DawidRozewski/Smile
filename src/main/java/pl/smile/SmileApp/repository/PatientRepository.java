package pl.smile.SmileApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient getByEmail(String email);
    Patient findByPesel(String pesel);

    Optional<Patient> findByEmail(String email);
    Optional<Patient> findById(long id);

    List<Patient> findAllByDoctor(Doctor doctorId);





}
