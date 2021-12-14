package pl.smile.SmileApp.service;

import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

public interface PatientService
{

    String save(Patient patient);
    List<Patient> listAll(String pesel, Doctor doctor);
    List<Patient> findAll();
    Optional<Patient> findById(long id);
    Patient findByEmail(String email);
    Patient update(Patient patient);
}
