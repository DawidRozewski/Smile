package pl.smile.SmileApp.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.repository.DoctorRepository;

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
    public Doctor findByEmail(String email) {
        return doctorRepository.getByEmail(email);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }


}
