package pl.smile.SmileApp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.service.DoctorServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorServiceImpl doctorService;

    public AdminController(PatientRepository patientRepository, DoctorRepository doctorRepository, DoctorServiceImpl doctorService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.doctorService = doctorService;
    }

    @GetMapping("/patients")
    public String showPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "/admin/patients";
    }

    @GetMapping("/patients/remove/{id}")
    public String prepareToRemove(@PathVariable long id, Model model) {
        model.addAttribute("patient", patientRepository.getById(id));
        return "/admin/patientRemove";
    }

    @PostMapping("/patients/remove/{id}")
    public String removePatient(@PathVariable long id,
                                @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            patientRepository.deleteById(id);
        }
        return "redirect:/admin/patients";
    }


    @GetMapping("/doctors")
    public String showDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "/admin/doctors";
    }

    @GetMapping("/doctors/remove/{id}")
    public String prepToRemove(@PathVariable long id, Model model) {
        model.addAttribute("doctor", doctorRepository.getById(id));
        return "/admin/doctorRemove";
    }

    @PostMapping("/doctors/remove/{id}")
    public String removeDoctor(@PathVariable long id,
                               @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            doctorRepository.deleteById(id);
        }
        return "redirect:/admin/doctors";
    }

    @GetMapping("/doctors/add")
    public String prepareToSave(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "/admin/add";
    }

    @PostMapping("/doctors/add")
    public String save(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/add";
        }
        doctorService.save(doctor, result);
        return "redirect:/admin/doctors";
    }
}
