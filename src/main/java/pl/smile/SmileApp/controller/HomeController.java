package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.AdminRepository;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.security.CurrentUser;

import java.security.Principal;

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
    public String homePage(Principal principal) {

        return "homepage";
    }

    @GetMapping("/test")
    public String test(Principal principal, Model model) {
        String name = principal.getName();
        Patient byEmail = patientRepository.getByEmail(name);
        model.addAttribute("patient", byEmail);
        return "/form/ediit";
    }


}


