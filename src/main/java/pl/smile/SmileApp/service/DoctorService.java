package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;

public interface DoctorService {

    String save(Doctor doctor, BindingResult result);


}
