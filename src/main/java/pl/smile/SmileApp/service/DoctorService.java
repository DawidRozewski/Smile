package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import java.security.Principal;
import java.util.List;

public interface DoctorService {

    Doctor save(Doctor doctor);
    Doctor update(Doctor doctor);
    Doctor getDoctor(Principal principal);
    String comparePasswords(Doctor doctor, BindingResult result);
    List<Doctor> getAllDoctors();
}
