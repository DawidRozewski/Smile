package pl.smile.SmileApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.smile.SmileApp.entity.Patient;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {

    private PatientService patientService;

    @Autowired
    public void setUserRepository(PatientService patientService) {
        this.patientService = patientService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Patient patient = patientService.findByEmail(email);
        if (patient == null) {throw new UsernameNotFoundException(email); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        patient.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new org.springframework.security.core.userdetails.User(
                patient.getEmail(), patient.getPassword(), grantedAuthorities);
    }
}
