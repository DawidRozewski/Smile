package pl.smile.SmileApp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.PatientRepository;

import javax.servlet.http.HttpSession;

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

    @Override
    public void update(Patient patient, HttpSession session, Model model) {
        Patient sessionPatient = (Patient) session.getAttribute("patient");
        patient.setPassword(sessionPatient.getPassword());
        patient.setPesel(sessionPatient.getPesel());
        patient.setConfirmed(sessionPatient.isConfirmed());
        model.addAttribute("patient", patient);
        patientRepository.save(patient);
    }


    private boolean checkPassword(String pass, String pass2) {
        return pass.equals(pass2);
    }
}
