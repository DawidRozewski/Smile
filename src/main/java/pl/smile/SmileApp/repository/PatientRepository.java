package pl.smile.SmileApp.repository;

import com.mysql.cj.jdbc.exceptions.PacketTooBigException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> getByEmail(String email);

    Patient findByEmail(String email);

    Patient findByPesel(String pesel);


}
