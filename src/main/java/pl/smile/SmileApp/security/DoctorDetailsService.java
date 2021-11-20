//package pl.smile.SmileApp.security;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import pl.smile.SmileApp.repository.DoctorRepository;
//
//@Component
//public class DoctorDetailsService implements UserDetailsService {
//
//    private final DoctorRepository doctorRepository;
//
//    public DoctorDetailsService(DoctorRepository doctorRepository) {
//        this.doctorRepository = doctorRepository;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return doctorRepository
//                .findByEmail(email)
//                .map(DoctorEntityDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException(email));
//    }
//}
