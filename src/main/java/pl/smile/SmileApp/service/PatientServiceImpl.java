package pl.smile.SmileApp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.PatientRepository;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientServiceImpl(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String save(Patient patient, BindingResult result) {
        if (checkPassword(patient.getPassword(), patient.getRepassword())) {
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
            patientRepository.save(patient);
        } else {
            result.rejectValue("repassword", "error.patient", "Podane hasła nie są zgodne.");
            return "/form/register";
        }
        return "/form/register";
    }

    @Override
    public void update(Patient patient) {
        patientRepository.save(patient);
    }

    private boolean checkPassword(String pass, String pass2) {
        return pass.equals(pass2);
    }
}
