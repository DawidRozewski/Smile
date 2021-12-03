package pl.smile.SmileApp.controller.form;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.service.PatientServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/app/register")
public class RegisterController {

    private final DoctorRepository doctorRepository;
    private final PatientServiceImpl patientService;

    @GetMapping("")
    public String prepareToAdd(Model model) {
        model.addAttribute("patient", new Patient());
        return "/form/register";
    }

    @PostMapping("")
    public String save(@ModelAttribute("patient") @Valid Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            return "/form/register";
        }
        patientService.save(patient,result);
        return "redirect:/login";
    }

    @ModelAttribute("doctors")
    public List<Doctor> doctorList() {
        return doctorRepository.findAll();
    }

}
