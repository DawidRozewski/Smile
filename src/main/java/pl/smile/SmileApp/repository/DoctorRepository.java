package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor getByEmail(String email);


}
