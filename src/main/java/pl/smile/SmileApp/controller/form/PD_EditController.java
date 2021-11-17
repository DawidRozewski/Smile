package pl.smile.SmileApp.controller.form;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.PatientRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/edit")
public class PD_EditController {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;


    public PD_EditController(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String prepareToEdit(HttpSession session, Model model) {
        Patient patient = (Patient) session.getAttribute("patient");
        model.addAttribute("patient", patientRepository.getById(patient.getId()));
        return "/form/edit";
    }

    @PostMapping("")
    public String merge(@ModelAttribute("patient") @Valid Patient patient, BindingResult result) {
        if(result.hasErrors()) {
            return "/edit";
        }
        patientRepository.save(patient);
        return "redirect:/patient/dashboard";

    }

}
