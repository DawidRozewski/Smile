package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;

import java.security.Principal;
import java.util.List;

public interface DoctorService  {

    String save(Doctor doctor);
    Doctor update(Doctor doctor);
    List<Doctor> findAllDoctors();
    Doctor getDoctor(Principal principal);
    String comparePasswords(Doctor doctor, BindingResult result);

}
