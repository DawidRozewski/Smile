package pl.smile.SmileApp.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.smile.SmileApp.entity.Admin;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.AdminRepository;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import java.util.Collections;

public class CurrentUser implements UserDetailsService {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private AdminRepository adminRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getUserDetails(email, patientRepository, doctorRepository);
    }

    private UserDetails getUserDetails(String email, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        Patient patient = patientRepository.getByEmail(email);
        UserDetails patientUser = getPatient(patient);
        if (patientUser != null) return patientUser;

        Doctor doctor = doctorRepository.getByEmail(email);
        UserDetails doctorUser = getDoctor(doctor);
        if (doctorUser != null) return doctorUser;

        Admin admin = adminRepository.findByUsername(email);
        UserDetails adminUser = getAdmin(admin);
        if(adminUser != null) return adminUser;

        throw new UsernameNotFoundException(email);
    }

    private UserDetails getDoctor(Doctor doctor) {
        if (doctor != null) {
            GrantedAuthority user = new SimpleGrantedAuthority(doctor.getROLE());
            return new User(doctor.getEmail(), doctor.getPassword(), Collections.singleton(user));
        }
        return null;
    }

    private UserDetails getPatient(Patient patient) {
        if (patient != null) {
            GrantedAuthority user = new SimpleGrantedAuthority(patient.getROLE());
            return new User(patient.getEmail(), patient.getPassword(), Collections.singleton(user));
        }
        return null;
    }
    private UserDetails getAdmin(Admin admin) {
        if (admin != null) {
            GrantedAuthority user = new SimpleGrantedAuthority(admin.getROLE());
            return new User(admin.getUsername(), admin.getPassword(), Collections.singleton(user));
        }
        return null;
    }

}

