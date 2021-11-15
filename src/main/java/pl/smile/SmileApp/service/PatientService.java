package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Patient;

public interface PatientService {

    String save(Patient patient, BindingResult result);

}
