package pl.smile.SmileApp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.entity.Service;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.repository.ServiceRepository;
import pl.smile.SmileApp.repository.TreatmentPlanRepository;
import pl.smile.SmileApp.service.PatientServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/app/patient")
//@SessionAttributes("patient")
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientServiceImpl patientService;
    private final PasswordEncoder passwordEncoder;
    private final ServiceRepository serviceRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientController(PatientRepository patientRepository, PatientServiceImpl patientService, PasswordEncoder passwordEncoder, ServiceRepository serviceRepository, TreatmentPlanRepository treatmentPlanRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
        this.passwordEncoder = passwordEncoder;
        this.serviceRepository = serviceRepository;
        this.treatmentPlanRepository = treatmentPlanRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/patient/dashboard";
    }

    @GetMapping("/history")
    public String history() {
        return "/patient/history";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("services", serviceRepository.findAll());
        return "/patient/services";
    }

    @GetMapping("/my-treatment")
    public String treatment(Principal principal, Model model) {
        String email = principal.getName();
        Patient byEmail = patientRepository.getByEmail(email);
        model.addAttribute("treatmentPlan", treatmentPlanRepository.findAllByPatientId(byEmail.getId()));
        return "/patient/treatment";
    }

    @GetMapping("/edit")
    public String prepareToEdit(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("patient", patientRepository.getByEmail(email));
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
    public String prepToAppointment(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "/patient/appointment";
    }
    @PostMapping("/appointment")
    public String addAppointment(@ModelAttribute("appointment") @Valid Appointment appointment, BindingResult result) {
        if(result.hasErrors()) {
            return "/patient/appointment";
        }
        appointmentRepository.save(appointment);
        return "/patient/appointment";
    }

    @ModelAttribute("services")
    public List<Service> servicesList() {
        return serviceRepository.findAll();
    }

    @ModelAttribute("hours")
    List<LocalTime> hourTimes () {
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


