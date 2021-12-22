package pl.smile.SmileApp.controller.doctor;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.exceptions.AppointmentNotFound;
import pl.smile.SmileApp.exceptions.PatientNotFound;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.service.DoctorService;
import pl.smile.SmileApp.service.PatientService;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/app/doctor")
public class D_ScheduleController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentRepository appointmentRepository;

    @GetMapping("/schedule")
    public String showSchedule(Principal principal, Model model) {
        Doctor doctor = doctorService.getDoctor(principal);
        model.addAttribute("appointment", appointmentRepository.findAllActiveAppointments(doctor.getId()));

        return "/doctor/schedule";

    }

    @GetMapping("/remove-appointment/{appID}/{patientID}")
    public String prepToRemoveVisit(@PathVariable Long appID, @PathVariable Long patientID, Model model) {
        Appointment appointment = appointmentRepository.findById(appID).orElseThrow(AppointmentNotFound::new);
        model.addAttribute("appointment", appointment);
        Patient patient = patientService.findById(patientID).orElseThrow(PatientNotFound::new);
        model.addAttribute("patient", patient.getFullName());

        return "/doctor/remove_appointment";
    }

    @PostMapping("/remove-appointment/{appID}/{patientID}")
    public String removeVisit(@PathVariable long appID, @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            appointmentRepository.deleteById(appID);
        }

        return "redirect:/app/doctor/schedule/";
    }





}
