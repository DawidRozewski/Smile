package pl.smile.SmileApp.controller.doctor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.entity.TreatmentPlan;
import pl.smile.SmileApp.exceptions.AppointmentNotFound;
import pl.smile.SmileApp.exceptions.PatientNotFound;
import pl.smile.SmileApp.exceptions.TreatmentPlanNotFound;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.repository.TreatmentPlanRepository;
import pl.smile.SmileApp.service.DoctorService;
import pl.smile.SmileApp.service.PatientService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
@RequestMapping("/app/doctor")
public class D_PatientController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final TreatmentPlanRepository treatmentRepository;
    private final AppointmentRepository appointmentRepository;

    @GetMapping("/patient/{patientID}")
    public String showPatientInfoAndTreatmentPan(@PathVariable long patientID, Model model, Principal principal) {
        Doctor doctor = doctorService.getDoctor(principal);
        Patient patient = patientService.findById(patientID).orElseThrow(PatientNotFound::new);
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointmentRepository.getFutureOrPresentPatientApp(patientID, doctor.getId(), LocalDate.now()));
        model.addAttribute("treatmentList", treatmentRepository.findAllByPatientIdAndDoctor(patientID, doctor));

        return "/doctor/patient";
    }

    @GetMapping("/add-treatment/{patientID}")
    public String prepToAddTreatment(@PathVariable long patientID, Principal principal, Model model) {
        Doctor doctor = doctorService.getDoctor(principal);
        model.addAttribute("doctor", doctor);
        model.addAttribute("patient", patientService.findById(patientID).orElseThrow(PatientNotFound::new));
        model.addAttribute("treatment", new TreatmentPlan());

        return "/doctor/treatment_plan";
    }

    @PostMapping("/add-treatment/{patientID}")
    public String addTreatment(@ModelAttribute("treatment")
                               @Valid TreatmentPlan treatmentPlan,
                               BindingResult result,
                               @PathVariable long patientID) {
        if(treatmentPlan.getVisitDate().getDayOfWeek() == DayOfWeek.SUNDAY || result.hasErrors()) {
            result.rejectValue("visitDate", "error.treatment", "We are not working on Sundays :)");
            return "/doctor/treatment_plan";
        }
        if(result.hasErrors()) {
            return "/doctor/treatment_plan";
        }

        treatmentRepository.save(treatmentPlan);

        return "redirect:/app/doctor/patient/" + patientID;
    }

    @GetMapping("/edit-treatment/{patientID}/{id}")
    public String prepToEditTreatment(@PathVariable long id, Model model) {
        TreatmentPlan treatmentPlan = treatmentRepository.findById(id).orElseThrow(TreatmentPlanNotFound::new);
        model.addAttribute("treatment", treatmentPlan);

        return "/doctor/treatment_plan";
    }

    @PostMapping("/edit-treatment/{patientID}/{id}")
    public String updateTreatment(@PathVariable long patientID,
                                  @ModelAttribute("treatment")
                                  @Valid TreatmentPlan treatmentPlan,
                                  BindingResult result) {
        if (treatmentPlan.getVisitDate().getDayOfWeek() == DayOfWeek.SUNDAY || result.hasErrors()) {
            result.rejectValue("visitDate", "error.treatment", "We are not working on Sundays :)");
            return "/doctor/treatment_plan";
        }
        if(result.hasErrors()) {
            return "/doctor/treatment_plan";
        }
        treatmentRepository.save(treatmentPlan);

        return "redirect:/app/doctor/patient/" + patientID;
    }

    @GetMapping("/remove/{patientID}/{id}")
    public String prepToRemoveTreatment(@PathVariable long id, Model model) {
        TreatmentPlan treatmentPlan = treatmentRepository.findById(id).orElseThrow(TreatmentPlanNotFound::new);
        model.addAttribute("treatment", treatmentPlan);

        return "/doctor/remove_treatment";
    }

    @PostMapping("/remove/{patientID}/{id}")
    public String removeTreatmentVisit(@PathVariable long id,
                                       @PathVariable long patientID,
                                       @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            treatmentRepository.deleteById(id);
        }

        return "redirect:/app/doctor/patient/" + patientID;
    }


    @GetMapping("/history/{patientID}")
    public String showPatientHistory(@PathVariable long patientID, Model model, Principal principal) {
        Patient patient = patientService.findById(patientID).orElseThrow(PatientNotFound::new);
        model.addAttribute("patient", patient);
        Doctor doctor = doctorService.getDoctor(principal);
        model.addAttribute("appointments", appointmentRepository.getPatientHistoryApp(patientID, doctor.getId()));

        return "/doctor/history";
    }

    @GetMapping("/endVisit/{id}")
    public String prepToEndVisit(@PathVariable long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(AppointmentNotFound::new);
        model.addAttribute("appointment", appointment);

        return "/doctor/end_visit";
    }

    @PostMapping("/endVisit/{id}")
    public String endVisit(@PathVariable long id, @RequestParam String confirmed) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(AppointmentNotFound::new);
        if ("yes".equals(confirmed)) {
            appointment.setFinished(true);
            appointmentRepository.save(appointment);
        }

        return "redirect:/app/doctor/patient/" + appointment.getPatient().getId();
    }






}
