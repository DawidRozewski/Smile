package pl.smile.SmileApp.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.smile.SmileApp.repository.PatientRepository;

@AllArgsConstructor
public class PatientDetailsService implements UserDetailsService {

    private final PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return patientRepository
                .findByEmail(email)
                .map(PatientEntityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(email));

    }
}
