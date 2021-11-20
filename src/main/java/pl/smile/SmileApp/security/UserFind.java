package pl.smile.SmileApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserFind implements UserDetailsService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public UserFind(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    private Map<String, User> roles = new HashMap<>();

    @PostConstruct
    public void init() {
        roles.put("admin2", new User("admin", "{noop}admin1", getAuthority()));
    }
    private List<GrantedAuthority> getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
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

