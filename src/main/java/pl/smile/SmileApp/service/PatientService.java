package pl.smile.SmileApp.service;

import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;

import java.util.List;

public interface PatientService {

    String save(Patient patient);
    List<Patient> listAll(String keyword, Doctor doctor);

}
