package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;

import java.security.Principal;
import java.util.List;

public interface PatientService {
    Patient save(Patient patient);
    List<Patient> listAll(String pesel, Doctor doctor);
    List<Patient> getAllPatients();
    Patient getById(long id);
    Patient update(Patient patient);
    Patient getPatient(Principal principal);
    String comparePasswords(Patient patient, BindingResult result);
}
