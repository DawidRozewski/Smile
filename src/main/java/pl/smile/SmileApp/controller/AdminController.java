package pl.smile.SmileApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    public AdminController(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/patients")
    public String showPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "/admin/patients";
    }

    @GetMapping("/remove/{id}")
    public String prepareToRemove(@PathVariable long id, Model model) {
        model.addAttribute("patient", patientRepository.getById(id));
        return "/admin/remove";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable long id,
                         @RequestParam String confirmed
                         ) {
        if("yes".equals(confirmed)) {
            patientRepository.deleteById(id);
        }
        return "redirect:/admin/patients";
    }




}
