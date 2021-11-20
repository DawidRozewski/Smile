package pl.smile.SmileApp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;


public class UserFind implements UserDetailsService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public UserFind(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Patient patientByEmail = patientRepository.getByEmail(email);
        if(patientByEmail != null) {
            return patientRepository
                    .findByEmail(email)
                    .map(PatientEntityDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException(email));
        } else if(doctorRepository.getByEmail(email) != null) {
            return doctorRepository
                    .findByEmail(email)
                    .map(DoctorEntityDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException(email));
        }
        throw new UsernameNotFoundException( email + " cannot be found");
    }


}

