package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;

import java.util.List;

public interface PatientService {

    String save(Patient patient, BindingResult result);
    void update(Patient patient);
    List<Patient> listAll(String keyword, Doctor doctor);

}
