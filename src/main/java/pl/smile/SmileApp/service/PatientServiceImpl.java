package pl.smile.SmileApp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.PatientRepository;

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
        }

        return "/form/login";
    }


    private boolean checkPassword(String pass, String pass2) {
        return pass.equals(pass2);
    }
}
