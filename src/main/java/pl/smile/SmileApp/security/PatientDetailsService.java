//package pl.smile.SmileApp.security;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import pl.smile.SmileApp.repository.PatientRepository;
//
//@Component
//public class PatientDetailsService implements UserDetailsService {
//
//    private final PatientRepository patientRepository;
//
//    public PatientDetailsService(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return patientRepository
//                .findByEmail(email)
//                .map(PatientEntityDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException(email));
//    }
//}
