package pl.smile.SmileApp.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.service.DoctorService;
import pl.smile.SmileApp.service.PatientService;
import javax.validation.Valid;

@Controller
@RequestMapping("/app/admin")
@AllArgsConstructor
public class AdminController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    @GetMapping("/patients")
    public String showPatients(Model model) {
        model.addAttribute("patients", patientService.findAll());

        return "/admin/patients";
    }

    @GetMapping("/doctors")
    public String showDoctors(Model model) {
        model.addAttribute("doctors", doctorService.findAllDoctors());

        return "/admin/doctors";
    }

    @GetMapping("/doctors/add")
    public String prepareToSave(Model model) {
        model.addAttribute("doctor", new Doctor());

        return "/admin/add";
    }

    @PostMapping("/doctors/add")
    public String save(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        doctorService.comparePasswords(doctor, result);

        if (result.hasErrors()) {
            return "/admin/add";
        }
        doctorService.save(doctor);

        return "redirect:/app/admin/doctors";
    }




}
