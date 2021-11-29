package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.smile.SmileApp.entity.*;
import pl.smile.SmileApp.repository.*;
import pl.smile.SmileApp.service.AppointmentServiceImpl;
import pl.smile.SmileApp.service.PatientServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/app/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientServiceImpl patientService;
    private final ServiceRepository serviceRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentServiceImpl appointmentService;

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        model.addAttribute("appointments",
                appointmentRepository.getFutureOrPresentAppointments(getPatientID(principal), LocalDate.now()));

        return "/patient/dashboard";
    }

    @GetMapping("/history")
    public String history(Principal principal, Model model) {
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
        model.addAttribute("patient", patientRepository.getByEmail(principal.getName()));

        return "/form/edit";
    }

    @PostMapping("/edit")
    public String updatePersonalData(@ModelAttribute("patient") Patient patient, BindingResult result) {
        if(result.hasErrors()) {
            return "/form/edit";
        }
        patientService.update(patient);

        return "redirect:/app/patient/dashboard";
    }
 
    @GetMapping("/appointment")
    public String prepToAppointment(@RequestParam long serviceID, Model model, Principal principal) {
        Patient patient = patientRepository.getByEmail(principal.getName());
        model.addAttribute("doctor", patient.getDoctor());
        model.addAttribute("patient", patient);
        model.addAttribute("service", serviceRepository.getById(serviceID));
        model.addAttribute("appointment", new Appointment());

        return "/patient/appointment";
    }
    @PostMapping("/appointment")
    public String addAppointment(@ModelAttribute("appointment") @Valid Appointment appointment, BindingResult result) {
        if(result.hasErrors()) {
            return "/patient/appointment";
        }
        appointmentRepository.save(appointment);

        return "redirect:/app/patient/dashboard";
    }

    @GetMapping("/appointment-by-plan")
    public String prepToAppByPan(@RequestParam long planID, Model model, Principal principal) {
        Patient patient = patientRepository.getByEmail(principal.getName());
        TreatmentPlan treatmentPlan = treatmentPlanRepository.getById(planID);
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
        Patient patient = patientRepository.getByEmail(patientEmail);
        return patient.getId();
    }
}


