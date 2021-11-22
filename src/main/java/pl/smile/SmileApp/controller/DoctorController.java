package pl.smile.SmileApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;

import java.security.Principal;


@Controller
@RequestMapping("/app/doctor")
public class DoctorController {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public DoctorController(DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        String email = principal.getName();
        Doctor doctor = doctorRepository.getByEmail(email);
        model.addAttribute("patients", patientRepository.findAllByDoctor(doctor));
        return "/doctor/dashboard";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "/doctor/schedule";
    }


    @GetMapping("/patient/{id}")
    public String patients(@PathVariable long id, Model model) {
        model.addAttribute("patient", patientRepository.getById(id));
       model.addAttribute("appointments",appointmentRepository.findAllByPatientId(id));
        return "/doctor/patient";
    }


    @GetMapping("/treatment-plan")
    public String treatmentPlan() {
        return "/doctor/treatmentP";
    }


    @GetMapping("/services")
    public String services() {
        return "/doctor/services";
    }










}
