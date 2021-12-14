package pl.smile.SmileApp.controller.patient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.entity.DentalService;
import pl.smile.SmileApp.repository.ServiceRepository;
import pl.smile.SmileApp.service.AppointmentServiceImpl;
import pl.smile.SmileApp.service.PatientServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/app/patient")
public class P_ServicesController {

    private final AppointmentServiceImpl appointmentService;
    private final PatientServiceImpl patientService;
    private final ServiceRepository serviceRepository;

    @GetMapping("/appointment")
    public String prepToAppointment(@RequestParam long serviceID, Model model, Principal principal) {
        Patient patient = patientService.getPatient(principal);
        LocalDate today = LocalDate.now();
        model.addAttribute("today", today);
        model.addAttribute("hoursDay", appointmentService.getAvailableHours(today));
        model.addAttribute("doctor", patient.getDoctor());
        model.addAttribute("patient", patient);
        model.addAttribute("service", serviceRepository.getById(serviceID));
        model.addAttribute("appointment", new Appointment());

        return "/patient/appointment";
    }
    @PostMapping("/appointment")
    public String addAppointment(@ModelAttribute("appointment") @Valid Appointment appointment, BindingResult result) {
        if(appointment.getDate().getDayOfWeek() == DayOfWeek.SUNDAY || result.hasErrors()) {
            result.rejectValue("date", "error.appointment", "W niedziele nie pracujemy :)");
            return "/patient/appointment";
        }
        appointmentService.save(appointment);

        return "redirect:/app/patient/dashboard";
    }


    @GetMapping("/services")
    public String services() {
        return "/patient/services";
    }


    @ModelAttribute("services")
    public List<DentalService> servicesList() {
        return serviceRepository.findAll();
    }

}
