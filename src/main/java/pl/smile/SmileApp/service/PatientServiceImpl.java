package pl.smile.SmileApp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.entity.Role;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.repository.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PatientServiceImpl implements PatientService {

   private final PatientRepository patientRepository;
   private final RoleRepository roleRepository;
   private final BCryptPasswordEncoder passwordEncoder;

    public PatientServiceImpl(PatientRepository patientRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    @Override
    public void savePatient(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setEnabled(1);
        Role patientRole = roleRepository.findByName("ROLE_PATIENT");
        patient.setRoles(new HashSet<Role>(List.of(patientRole)));
        patientRepository.save(patient);
    }
}
