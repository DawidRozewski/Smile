package pl.smile.SmileApp.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.repository.DoctorRepository;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String save(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorRepository.save(doctor);

        return "/admin/doctors";
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctor(Principal principal) {
        return doctorRepository.getByEmail(principal.getName());
    }

    @Override
    public String comparePasswords(Doctor doctor, BindingResult result) {
        if (!checkPassword(doctor.getPassword(), doctor.getRepassword())) {
            result.rejectValue("repassword", "error.doctor", "Podane hasła nie są zgodne.");
            return "/admin/add";
        }
        return null;
    }

    private boolean checkPassword(String pass, String pass2) {
        return pass.equals(pass2);
    }

}
