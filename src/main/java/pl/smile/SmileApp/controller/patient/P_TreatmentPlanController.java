package pl.smile.SmileApp.controller.patient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.entity.TreatmentPlan;
import pl.smile.SmileApp.exceptions.TreatmentPlanNotFound;
import pl.smile.SmileApp.repository.TreatmentPlanRepository;
import pl.smile.SmileApp.service.AppointmentServiceImpl;
import pl.smile.SmileApp.service.PatientServiceImpl;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/app/patient")
public class P_TreatmentPlanController {

        private final PatientServiceImpl patientService;
        private final TreatmentPlanRepository treatmentPlanRepository;
        private final AppointmentServiceImpl appointmentService;

    @GetMapping("/appointment-by-plan")
    public String prepToAppByPan(@RequestParam long planID, Model model, Principal principal) {
        Patient patient = patientService.getPatient(principal);
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
        appointmentService.save(appointment);

        return "redirect:/app/patient/dashboard";
    }


    @GetMapping("/my-treatment")
    public String treatment(Principal principal, Model model) {
        Patient patient = patientService.getPatient(principal);
        model.addAttribute("treatmentPlan",
                treatmentPlanRepository.findAllByPatientId(patient.getId()));

        return "/patient/treatment";
    }

}
