package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.exceptions.DoctorNotFound;
import pl.smile.SmileApp.exceptions.PatientNotFound;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.service.DoctorServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/app/admin")
@AllArgsConstructor
public class AdminController {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorServiceImpl doctorService;

    @GetMapping("/patients")
    public String showPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());

        return "/admin/patients";
    }

    @GetMapping("/doctors")
    public String showDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "/admin/doctors";
    }

    @GetMapping("/doctors/add")
    public String prepareToSave(Model model) {
        model.addAttribute("doctor", new Doctor());

        return "/admin/add";
    }

    @PostMapping("/doctors/add")
    public String save(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        if (!checkPassword(doctor.getPassword(), doctor.getRepassword())) {
            result.rejectValue("repassword", "error.doctor", "Podane hasła nie są zgodne.");
            return "/admin/add";
        }
        if (result.hasErrors()) {
            return "/admin/add";
        }
        doctorService.save(doctor);

        return "redirect:/app/admin/doctors";
    }

    private boolean checkPassword(String pass, String pass2) {
        return pass.equals(pass2);
    }
}
