package pl.smile.SmileApp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.service.ErrorMessageHandling;
import pl.smile.SmileApp.service.PatientService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService, ErrorMessageHandling {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Patient save(Patient patient) {
        setEncodedPassword(patient);
        return patientRepository.save(patient);
    }

    private void setEncodedPassword(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
    }

    @Override
    public Patient getPatient(Principal principal) {
        String patientEmail = getEmail(principal);
        return patientRepository.getByEmail(patientEmail);
    }

    private String getEmail(Principal principal) {
        return principal.getName();
    }

    @Override
    public List<Patient> listAll(String pesel, Doctor doctor) {
        if (peselWasGiven(pesel)) {
            return patientRepository.findAllByPesel(pesel);
        }
        return patientRepository.findAllByDoctor(doctor);
    }

    private boolean peselWasGiven(String pesel) {
        return pesel != null;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient update(Patient patient) {
        Patient updatedPatient = getById(patient.getId());
        patientRepository.save(updatedPatient);
        return updatedPatient;
    }

    @Override
    public Patient getById(long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Patient", id));
    }

    @Override
    public String comparePasswords(Patient patient, BindingResult result) {
        if (passwordsDoesntMatch(patient.getPassword(), patient.getRepassword())) {
            setErrorMessageToView(result);
            return "/form/register";
        }
        return null;
    }

    private boolean passwordsDoesntMatch(String pass, String pass2) {
        return !pass.equals(pass2);
    }

    @Override
    public void setErrorMessageToView(BindingResult result) {
        result.rejectValue("repassword", "error.patient", "Passwords do not match.");
    }


}
