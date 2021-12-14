package pl.smile.SmileApp.service;

import pl.smile.SmileApp.entity.Doctor;

import java.security.Principal;
import java.util.List;

public interface DoctorService  {

    String save(Doctor doctor);
    Doctor update(Doctor doctor);
    Doctor findByEmail(String email);
    List<Doctor> findAll();
    Doctor getDoctor(Principal principal);


}
