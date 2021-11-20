package pl.smile.SmileApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.security.UserFind;

import java.security.Principal;


@Controller
@RequestMapping("/app")
public class HomeController {

    private final UserFind userFind;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public HomeController(UserFind userFind, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.userFind = userFind;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("")
    public String showHomePage() {
            return "homepage";
    }



}
