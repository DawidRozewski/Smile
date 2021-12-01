package pl.smile.SmileApp.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.PatientRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

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

    public List<Patient> listAll(String pesel, Doctor doctor) {
        if (pesel != null) {
            return patientRepository.findAllByPesel(pesel);
        }
        return patientRepository.findAllByDoctor(doctor);
    }


    private boolean checkPassword(String pass, String pass2) {
        return pass.equals(pass2);
    }
}
