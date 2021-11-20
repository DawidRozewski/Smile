package pl.smile.SmileApp.controller.form;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;


@Controller
@SessionAttributes({"patient", "doctor", "admin"})
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    public LoginController(PasswordEncoder passwordEncoder, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;

    }



//    @PostMapping("/login")
//    public String showForm(
//
//
//
//        return "redirect:/app/patient/dashboard";
//    }


//    @GetMapping("")
//    public String showForm(Model model, String error) {
//        if(error != null) {
//            model.addAttribute("errorMsg", "Nieprawidłowe dane.");
//            return "/form/login";
//        }
//        return "/app";
//    }

    private boolean checkPassword(String pass, String pass2) {
        return pass.equals(pass2);
    }
}
