package pl.smile.SmileApp.service;

import pl.smile.SmileApp.entity.Patient;


public interface PatientService {

    Patient findByEmail(String email);

    void savePatient(Patient patient);



}
