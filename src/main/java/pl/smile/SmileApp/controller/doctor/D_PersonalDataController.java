package pl.smile.SmileApp.controller.doctor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.service.DoctorService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/app/doctor")
public class D_PersonalDataController {

    private final DoctorService doctorService;

    @GetMapping("/edit")
    public String prepToEditPD(Principal principal, Model model) {
        Doctor doctor = doctorService.getDoctor(principal);
        model.addAttribute("doctor", doctor);
        return "/doctor/edit";
    }

    @PostMapping("/edit")
    public String updatePD(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        if(result.hasErrors()) {
            return "/doctor/edit";
        }

        doctorService.update(doctor);
        return "redirect:/app/doctor/dashboard";
    }
}
