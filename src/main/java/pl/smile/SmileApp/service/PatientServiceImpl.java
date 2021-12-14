package pl.smile.SmileApp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.exceptions.PatientNotFound;
import pl.smile.SmileApp.repository.PatientRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service

public  class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    protected PatientServiceImpl(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String save(Patient patient) {
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
            patientRepository.save(patient);

        return "/form/register";
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
    public Patient findByEmail(String email) {
        return patientRepository.getByEmail(email);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(Principal principal) {
        String email = principal.getName();
        return patientRepository.getByEmail(email);
    }

}
