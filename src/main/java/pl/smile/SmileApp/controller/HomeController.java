package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.AppointmentSlot;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.AdminRepository;
import pl.smile.SmileApp.repository.AppointmentSlotRepository;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.security.CurrentUser;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class HomeController {

    private final CurrentUser currentUser;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppointmentSlotRepository appointmentSlotRepository;


    @GetMapping("")
    public String homePage(Principal principal) {

        return "homepage";
    }

    @GetMapping("/test")
    public String test(Model model) {


        return "/form/ediit";
    }

    @ModelAttribute("hours")
    List< LocalTime > hourTimes () {
        return List.of(
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0)
        );
    }
}


