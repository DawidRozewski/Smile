package pl.smile.SmileApp.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.repository.DoctorRepository;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String save(Doctor doctor, BindingResult result) {
        if (checkPassword(doctor.getPassword(), doctor.getRepassword())) {
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
            doctorRepository.save(doctor);
        } else {
            result.rejectValue("repassword", "error.doctor", "Podane hasła nie są zgodne.");
            return "/admin/add";
        }

        return "/admin/doctors";
    }

    private boolean checkPassword(String pass, String pass2) {
        return pass.equals(pass2);
    }





}
