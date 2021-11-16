package pl.smile.SmileApp.controller;

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
@RequestMapping("/register")
public class RegisterController {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientServiceImpl patientService;

    public RegisterController(PatientRepository patientRepository, DoctorRepository doctorRepository, PasswordEncoder passwordEncoder, PatientServiceImpl patientService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
        this.patientService = patientService;
    }

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

//    Patient byEmail = patientRepository.findByEmail(patient.getEmail());
//        if(byEmail != null) {
//        result.rejectValue("email", "error.patient", "Podany e-mail istnieje już w bazie.");
//    }
//    Patient byPesel = patientRepository.findByPesel(patient.getPesel());
//        if(byPesel != null) {
//        result.rejectValue("pesel", "error.patient", "Podany pesel istnieje już w bazie.");
//    }

    @ModelAttribute("doctors")
    public List<Doctor> doctorList() {
        return doctorRepository.findAll();
    }

}
