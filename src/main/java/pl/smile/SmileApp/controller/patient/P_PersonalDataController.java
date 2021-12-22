package pl.smile.SmileApp.controller.patient;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.service.PatientService;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/app/patient")
public class P_PersonalDataController {

    private final PatientService patientService;


    @GetMapping("/edit")
    public String prepareToEdit(Principal principal, Model model) {
        Patient patient = patientService.getPatient(principal);
        model.addAttribute("patient", patient);

        return "/form/edit";
    }

    @PostMapping("/edit")
    public String updatePersonalData(@ModelAttribute("patient") Patient patient) {
        patientService.update(patient);
        return "redirect:/app/patient/dashboard";
    }


}
