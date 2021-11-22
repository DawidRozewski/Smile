package pl.smile.SmileApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.TreatmentPlan;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.repository.TreatmentPlanRepository;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/app/doctor")
public class DoctorController {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;

    public DoctorController(DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository, TreatmentPlanRepository treatmentPlanRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.treatmentPlanRepository = treatmentPlanRepository;
    }

    @GetMapping("/dashboard")
    public String showAllPatients(Principal principal, Model model) {
        String email = principal.getName();
        Doctor doctor = doctorRepository.getByEmail(email);
        model.addAttribute("patients", patientRepository.findAllByDoctor(doctor));
        return "/doctor/dashboard";
    }

//    -----------------------------------------------------
    @GetMapping("/patient/{id}")
    public String showPatientInfoAndTreatmentPan(@PathVariable long id, Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("treatment", new TreatmentPlan());
        model.addAttribute("patient", patientRepository.getById(id));
        model.addAttribute("appointments", appointmentRepository.findAllByPatientId(id));
        model.addAttribute("doctor",doctorRepository.getByEmail(email));
        model.addAttribute("treatmentList", treatmentPlanRepository.findAllByPatientIdAndDoctor(id, doctorRepository.getByEmail(email)));
        return "/doctor/patient";
    }

    @PostMapping("/patient/{id}")
    public String addTreatmentPlan(@ModelAttribute("treatment") @Valid TreatmentPlan treatmentPlan, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/app/doctor/patient/{id}";
        }
        treatmentPlanRepository.save(treatmentPlan);
        return "redirect:/app/doctor/patient/{id}";
    }


    @GetMapping("/history/{id}")
    public String showPatientHistory(@PathVariable long id, Model model) {
        model.addAttribute("appointments", appointmentRepository.findAllByPatientId(id));
        return "/doctor/history";
    }


    @GetMapping("/schedule")
    public String schedule() {
        return "/doctor/schedule";
    }

    @GetMapping("/services")
    public String services() {
        return "/doctor/services";
    }


}
