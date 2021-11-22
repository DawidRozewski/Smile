package pl.smile.SmileApp.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Patient;

import javax.servlet.http.HttpSession;

public interface PatientService {

    String save(Patient patient, BindingResult result);
    void update(Patient patient);


}
