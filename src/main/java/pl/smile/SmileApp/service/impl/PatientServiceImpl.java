package pl.smile.SmileApp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.service.PatientService;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public  class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String save(Patient patient) {
        encodePassword(patient);
        patientRepository.save(patient);
        return "/form/register";
    }

    private void encodePassword(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
    }

    @Override
    public Patient getPatient(Principal principal) {
        String email = principal.getName();
        return patientRepository.getByEmail(email);
    }

    @Override
    public List<Patient> listAll(String pesel, Doctor doctor) {
        if (pesel != null) {
            return patientRepository.findAllByPesel(pesel);
        }
        return patientRepository.findAllByDoctor(doctor);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public String comparePasswords(Patient patient, BindingResult result) {
        if(!comparePasswords(patient.getPassword(), patient.getRepassword())) {
            result.rejectValue("repassword", "error.patient", "Passwords do not match.");
            return "/form/register";
        }
        return null;
    }

    private boolean comparePasswords(String pass, String pass2) {
        return pass.equals(pass2);
    }


}
