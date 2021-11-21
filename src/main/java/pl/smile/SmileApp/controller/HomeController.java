package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.smile.SmileApp.repository.AdminRepository;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.security.CurrentUser;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class HomeController {

    private final CurrentUser currentUser;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("")
    public String homePage() {
        return "homepage";
    }


}


