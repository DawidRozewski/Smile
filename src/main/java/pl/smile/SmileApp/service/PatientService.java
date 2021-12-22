package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.PatientRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PatientService
{

    String save(Patient patient);
    List<Patient> listAll(String pesel, Doctor doctor);
    List<Patient> findAll();
    Optional<Patient> findById(long id);
    Patient update(Patient patient);
    Patient getPatient(Principal principal);
    String comparePasswords(Patient patient, BindingResult result);
}
