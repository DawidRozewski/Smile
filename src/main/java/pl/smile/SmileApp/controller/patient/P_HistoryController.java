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

@Controller
@RequestMapping("/app/patient")
@AllArgsConstructor
public class P_HistoryController {

    private final PatientService patientService;
    private final AppointmentRepository appointmentRepository;

    @GetMapping("/history")
    public String history(Principal principal, Model model) {
        Patient patient = patientService.getPatient(principal);
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointmentRepository.getPastAppointments(patient.getId()));
        return "/patient/history";
    }
}
