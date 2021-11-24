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

    @GetMapping("")
    public String homePage() {

        return "homepage";
    }
}



