package pl.smile.SmileApp.controller.form;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;


@Controller
@RequestMapping("/login")
@SessionAttributes("patient")
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public LoginController(PasswordEncoder passwordEncoder, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("")
    public String showForm() {
        return "/form/login";
    }

    @PostMapping("")
    public String login(@RequestParam String email,
                        @RequestParam String password, Model model) {
        Patient patient = patientRepository.findByEmail(email);
        if (patient != null) {
            if (passwordEncoder.matches(password, patient.getPassword())) {
                model.addAttribute("patient", patient);
                return "/patient/dashboard";
            }
        }
        return "redirect:/login";
    }
}
