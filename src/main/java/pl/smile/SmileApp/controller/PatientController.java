package pl.smile.SmileApp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.service.PatientServiceImpl;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientServiceImpl patientService;
    private final PasswordEncoder passwordEncoder;

    public PatientController(PatientRepository patientRepository, PatientServiceImpl patientService, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/patient/dashboard";
    }


    @GetMapping("/history")
    public String history() {
        return "/patient/history";
    }

    @GetMapping("/services")
    public String services() {
        return "/patient/services";
    }

    @GetMapping("/my-treatment")
    public String treatment() {
        return "/patient/treatment";
    }

    @GetMapping("/appointment")
    public String appointment() {
        return "/patient/appointment";
    }

//
    @GetMapping("/edit")
    public String prepareToEdit(HttpSession session, Model model) {
        Patient patient = (Patient) session.getAttribute("patient");
        model.addAttribute("patient", patientRepository.getById(patient.getId()));
        return "/form/edit";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("patient") Patient patient, HttpSession session, Model model) {
        patientService.update(patient,session,model);
        return "redirect:/patient/dashboard";
    }


}


