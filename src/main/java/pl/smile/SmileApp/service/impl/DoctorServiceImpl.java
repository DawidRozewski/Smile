package pl.smile.SmileApp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.service.DoctorService;
import pl.smile.SmileApp.service.ErrorMessageHandling;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService, ErrorMessageHandling {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Doctor save(Doctor doctor) {
        setEncodedPassword(doctor);
        return doctorRepository.save(doctor);
    }

    private void setEncodedPassword(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
    }

    @Override
    public Doctor update(Doctor doctor) {
        Doctor updatedDoctor = getDoctorById(doctor.getId());
        return doctorRepository.save(updatedDoctor);
    }

    private Doctor getDoctorById(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Doctor", id));
    }

    @Override
    public Doctor getDoctor(Principal principal) {
        return doctorRepository.getByEmail(principal.getName());
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public String comparePasswords(Doctor doctor, BindingResult result) {
        if (passwordsDoesntMatch(doctor.getPassword(), doctor.getRepassword())) {
            setErrorMessageToView(result);
            return "/admin/add";
        }
        return null;
    }

    private boolean passwordsDoesntMatch(String pass, String pass2) {
        return !pass.equals(pass2);
    }

    @Override
    public void setErrorMessageToView(BindingResult result) {
        result.rejectValue("repassword", "error.doctor", "Passwords do not match.");
    }
}
