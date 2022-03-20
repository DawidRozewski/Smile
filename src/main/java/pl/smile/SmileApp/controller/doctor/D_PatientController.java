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
import pl.smile.SmileApp.service.AppointmentService;
import pl.smile.SmileApp.service.DoctorService;
import pl.smile.SmileApp.service.PatientService;
import pl.smile.SmileApp.service.TreatmentPlanService;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
@RequestMapping("/app/doctor")
public class D_PatientController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final TreatmentPlanService treatmentPlanService;
    private final AppointmentService appointmentService;

    @GetMapping("/patient/{patientID}")
    public String showPatientInfoAndTreatmentPan(@PathVariable long patientID, Model model, Principal principal) {
        Doctor doctor = doctorService.getDoctor(principal);
        Patient patient = patientService.getById(patientID);
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointmentService.getFutureOrPresentPatientApp(patientID, doctor.getId(), LocalDate.now()));
        model.addAttribute("treatmentList", treatmentPlanService.getAllByPatientIdAndDoctor(patientID, doctor));

        return "/doctor/patient";
    }

    @GetMapping("/add-treatment/{patientID}")
    public String prepToAddTreatment(@PathVariable long patientID, Principal principal, Model model) {
        Doctor doctor = doctorService.getDoctor(principal);
        model.addAttribute("doctor", doctor);
        model.addAttribute("patient", patientService.getById(patientID));
        model.addAttribute("treatment", new TreatmentPlan());

        return "/doctor/treatment_plan";
    }

    @PostMapping("/add-treatment/{patientID}")
    public String addTreatment(@ModelAttribute("treatment")
                               @Valid TreatmentPlan treatmentPlan,
                               BindingResult result,
                               @PathVariable long patientID) {
        if (result.hasErrors()) {
            return "/doctor/treatment_plan";
        }
        if (treatmentPlanService.ifSundayThrowMessage(treatmentPlan, result)) {
            return "/doctor/treatment_plan";
        }
        treatmentPlanService.save(treatmentPlan);

        return "redirect:/app/doctor/patient/" + patientID;
    }

    @GetMapping("/edit-treatment/{patientID}/{id}")
    public String prepToEditTreatment(@PathVariable long id, Model model) {
        TreatmentPlan treatmentPlan = treatmentPlanService.getById(id);
        model.addAttribute("treatment", treatmentPlan);

        return "/doctor/treatment_plan";
    }

    @PostMapping("/edit-treatment/{patientID}/{id}")
    public String updateTreatment(@PathVariable long patientID,
                                  @ModelAttribute("treatment")
                                  @Valid TreatmentPlan treatmentPlan,
                                  BindingResult result) {
        if (treatmentPlanService.ifSundayThrowMessage(treatmentPlan, result)) {
            return "/doctor/treatment_plan";
        }
        if (result.hasErrors()) {
            return "/doctor/treatment_plan";
        }
        treatmentPlanService.save(treatmentPlan);

        return "redirect:/app/doctor/patient/" + patientID;
    }

    @GetMapping("/remove/{patientID}/{id}")
    public String prepToRemoveTreatment(@PathVariable long id, Model model) {
        TreatmentPlan treatmentPlan = treatmentPlanService.getById(id);
        model.addAttribute("treatment", treatmentPlan);

        return "/doctor/remove_treatment";
    }

    @PostMapping("/remove/{patientID}/{id}")
    public String removeTreatmentVisit(@PathVariable long id,
                                       @PathVariable long patientID,
                                       @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            treatmentPlanService.delete(id);
        }

        return "redirect:/app/doctor/patient/" + patientID;
    }


    @GetMapping("/history/{patientID}")
    public String showPatientHistory(@PathVariable long patientID, Model model, Principal principal) {
        Patient patient = patientService.getById(patientID);
        model.addAttribute("patient", patient);
        Doctor doctor = doctorService.getDoctor(principal);
        model.addAttribute("appointments", appointmentService.getPatientHistoryApp(patientID, doctor.getId()));

        return "/doctor/history";
    }

    @GetMapping("/endVisit/{id}")
    public String prepToEndVisit(@PathVariable long id, Model model) {
        Appointment appointment = appointmentService.getById(id);
        model.addAttribute("appointment", appointment);

        return "/doctor/end_visit";
    }

    @PostMapping("/endVisit/{id}")
    public String endVisit(@PathVariable long id, @RequestParam String confirmed) {
        Appointment appointment = appointmentService.getById(id);
        if ("yes".equals(confirmed)) {
            appointment.setFinished(true);
            appointmentService.save(appointment);
        }

        return "redirect:/app/doctor/patient/" + appointment.getPatient().getId();
    }


}
