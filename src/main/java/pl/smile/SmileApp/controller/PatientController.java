package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.smile.SmileApp.entity.*;
import pl.smile.SmileApp.exceptions.TreatmentPlanNotFound;
import pl.smile.SmileApp.repository.*;
import pl.smile.SmileApp.service.AppointmentServiceImpl;
import pl.smile.SmileApp.service.PatientServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/app/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientServiceImpl patientService;
    private final AppointmentServiceImpl appointmentService;
    private final ServiceRepository serviceRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final AppointmentRepository appointmentRepository;

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        model.addAttribute("appointments",
                appointmentRepository.getFutureOrPresentAppointments(getPatientID(principal), LocalDate.now()));

        return "/patient/dashboard";
    }

    @GetMapping("/history")
    public String history(Principal principal, Model model) {
        model.addAttribute("patient", getPatient(principal));
        model.addAttribute("appointments",
                appointmentRepository.getPastAppointments(getPatientID(principal)));

        return "/patient/history";
    }

    @GetMapping("/services")
    public String services() {
        return "/patient/services";
    }

    @GetMapping("/my-treatment")
    public String treatment(Principal principal, Model model) {
        model.addAttribute("treatmentPlan",
                treatmentPlanRepository.findAllByPatientId(getPatientID(principal)));

        return "/patient/treatment";
    }

    @GetMapping("/edit")
    public String prepareToEdit(Principal principal, Model model) {
        model.addAttribute("patient", getPatient(principal));

        return "/form/edit";
    }

    @PostMapping("/edit")
    public String updatePersonalData(@ModelAttribute("patient") Patient patient) {

        patientService.update(patient);
        return "redirect:/app/patient/dashboard";
    }
 
    @GetMapping("/appointment")
    public String prepToAppointment(@RequestParam long serviceID, Model model, Principal principal) {
        Patient patient = getPatient(principal);
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
        if(appointment.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            result.rejectValue("date", "error.appointment", "W niedziele nie pracujemy :)");
            return "/patient/appointment";
        }

        if(result.hasErrors()) {
            return "/patient/appointment";
        }
        appointmentRepository.save(appointment);

        return "redirect:/app/patient/dashboard";
    }

    @GetMapping("/appointment-by-plan")
    public String prepToAppByPan(@RequestParam long planID, Model model, Principal principal) {
        Patient patient = getPatient(principal);
        TreatmentPlan treatmentPlan = treatmentPlanRepository.findById(planID).orElseThrow(TreatmentPlanNotFound::new);
        model.addAttribute("doctor", patient.getDoctor());
        model.addAttribute("patient", patient);
        model.addAttribute("treatment", treatmentPlan);
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("hoursDay", appointmentService.getAvailableHours(treatmentPlan.getVisitDate()));

        return "/patient/appointment_by_plan";
    }
    @PostMapping("/appointment-by-plan")
    public String addAppByPLan(@ModelAttribute("appointment") @Valid Appointment appointment, BindingResult result) {
        if(result.hasErrors()) {
            return "/patient/appointment_by_plan";
        }
        appointmentRepository.save(appointment);

        return "redirect:/app/patient/dashboard";
    }

    @ModelAttribute("services")
    public List<Service> servicesList() {
        return serviceRepository.findAll();
    }

    private long getPatientID(Principal principal) {
        String patientEmail = principal.getName();
        Patient patient = patientService.findByEmail(patientEmail);
        return patient.getId();
    }

    private Patient getPatient(Principal principal) {
        String email = principal.getName();
        return patientService.findByEmail(email);
    }



}


