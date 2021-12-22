package pl.smile.SmileApp.controller.patient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.service.PatientService;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
@RequestMapping("/app/patient")
public class P_DashboardController {


    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;


    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        Patient patient = patientService.getPatient(principal);
        model.addAttribute("appointments",
                appointmentRepository.getFutureOrPresentAppointments(patient.getId(), LocalDate.now()));

        return "/patient/dashboard";
    }



}
