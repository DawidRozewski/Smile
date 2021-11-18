package pl.smile.SmileApp.controller.form;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.exceptions.EmailAlreadyExist;
import pl.smile.SmileApp.model.Admin;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;


@Controller
@RequestMapping("/app/login")
@SessionAttributes({"patient", "doctor", "admin"})
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final Admin admin;

    public LoginController(PasswordEncoder passwordEncoder, PatientRepository patientRepository, DoctorRepository doctorRepository, Admin admin) {
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.admin = admin;
    }

    @GetMapping("")
    public String showForm() {
        return "/form/login";
    }

    @PostMapping("")
    public String login(@RequestParam String email,
                        @RequestParam String password, Model model) {
        Patient patient = patientRepository.findByEmail(email).orElseThrow(EmailAlreadyExist::new);
//        if (patient != null) {
//            if (passwordEncoder.matches(password, patient.getPassword())) {
//                model.addAttribute("patient", patient);
//                return "/patient/dashboard";
//            }
//        }
//
//        Doctor doctor = doctorRepository.findByEmail(email);
//        if (doctor != null) {
//            if (passwordEncoder.matches(password, doctor.getPassword())) {
//                model.addAttribute("doctor", doctor);
//                return "/doctor/dashboard";
//            }
//        }
//
//        if (admin.getADMIN_PW().equals(email)) {
//            if (admin.getADMIN_PW().equals(password)) {
//                model.addAttribute("admin", admin);
//                return "/admin/patients";
//            }
//        }

        return "redirect:/login";
    }
}
