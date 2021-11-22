package pl.smile.SmileApp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.entity.Services;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.repository.ServicesRepository;
import pl.smile.SmileApp.service.PatientServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/app/patient")
//@SessionAttributes("patient")
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientServiceImpl patientService;
    private final PasswordEncoder passwordEncoder;
    private final ServicesRepository servicesRepository;

    public PatientController(PatientRepository patientRepository, PatientServiceImpl patientService, PasswordEncoder passwordEncoder, ServicesRepository servicesRepository) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
        this.passwordEncoder = passwordEncoder;
        this.servicesRepository = servicesRepository;
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
    public String services(Model model) {
        model.addAttribute("services", servicesRepository.findAll());
        return "/patient/services";
    }

    @GetMapping("/my-treatment")
    public String treatment() {
        return "/patient/treatment";
    }

    @GetMapping("/appointment")
    public String prepToAppointment(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "/patient/appointment";
    }


    @GetMapping("/edit")
    public String prepareToEdit(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("patient", patientRepository.getByEmail(email));
        return "/form/edit";
    }

    @PostMapping("/edit")
    public String updatePersonalData(@ModelAttribute("patient") Patient patient, BindingResult result) {
        if(result.hasErrors()) {
            return "/form/edit";
        }
        patientService.update(patient);
        return "redirect:/app/patient/dashboard";
    }

    @ModelAttribute("services")
    public List<Services> servicesList() {
        return servicesRepository.findAll();
    }

}


