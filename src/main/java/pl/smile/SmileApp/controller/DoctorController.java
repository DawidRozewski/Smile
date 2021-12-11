package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.*;
import pl.smile.SmileApp.exceptions.*;
import pl.smile.SmileApp.repository.*;
import pl.smile.SmileApp.service.DoctorServiceImpl;
import pl.smile.SmileApp.service.PatientServiceImpl;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/app/doctor")
@AllArgsConstructor
public class DoctorController {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final TreatmentPlanRepository treatmentRepository;
    private final ServiceRepository serviceRepository;
    private final PatientServiceImpl patientService;

    @GetMapping("/dashboard")
    public String showAllPatients(Principal principal, Model model, @Param("pesel") String pesel) {
        Doctor doctor = getDoctor(principal);
        model.addAttribute("patients", patientService.listAll(pesel, doctor));

        return "/doctor/dashboard";
    }

    @GetMapping("/schedule")
    public String showSchedule(Principal principal, Model model) {
        Doctor doctor = getDoctor(principal);
        model.addAttribute("appointment", appointmentRepository.findAllActiveAppointments(doctor.getId()));

        return "/doctor/schedule";

    }

    @GetMapping("/patient/{patientID}")
    public String showPatientInfoAndTreatmentPlan(@PathVariable long patientID, Model model, Principal principal) {
        Doctor doctor = getDoctor(principal);
        Patient patient = patientRepository.findById(patientID).orElseThrow(PatientNotFound::new);
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointmentRepository.getFutureOrPresentPatientApp(patientID, doctor.getId(), LocalDate.now()));
        model.addAttribute("treatmentList", treatmentRepository.findAllByPatientIdAndDoctor(patientID, doctor));

        return "/doctor/patient";
    }

    @GetMapping("/add-treatment/{patientID}")
    public String prepToAddTreatment(@PathVariable long patientID, Principal principal, Model model) {
        model.addAttribute("patient", patientRepository.findById(patientID).orElseThrow(PatientNotFound::new));
        model.addAttribute("treatment", new TreatmentPlan());
        model.addAttribute("doctor", getDoctor(principal));

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
        if (result.hasErrors()) {
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

    @GetMapping("/services")
    public String prepToAddService(Model model) {
        model.addAttribute("service", new Service());
        model.addAttribute("services", serviceRepository.findAll());

        return "/doctor/services";
    }

    @PostMapping("/services")
    public String addService(@ModelAttribute("service") @Valid Service service, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/app/doctor/services";
        }
        serviceRepository.save(service);

        return "redirect:/app/doctor/services";
    }

    @GetMapping("/edit-service/{id}")
    public String prepToEditService(@PathVariable long id, Model model) {
        Service service = serviceRepository.findById(id).orElseThrow(ServiceNotFound::new);
        model.addAttribute("service", service);
        model.addAttribute("services", serviceRepository.findAll());

        return "/doctor/services";
    }

    @PostMapping("/edit-service/{id}")
    public String editService(@ModelAttribute("service") @Valid Service service, BindingResult result) {
        if (result.hasErrors()) {
            return "/doctor/services";
        }
        serviceRepository.save(service);

        return "redirect:/app/doctor/services";
    }

    @GetMapping("/remove-service/{id}")
    public String prepToDeleteService(@PathVariable long id, Model model) {
        Service service = serviceRepository.findById(id).orElseThrow(ServiceNotFound::new);
        model.addAttribute("service", service);

        return "/doctor/remove_service";
    }

    @PostMapping("/remove-service/{id}")
    public String deleteService(@PathVariable long id, @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            serviceRepository.deleteById(id);
        }

        return "redirect:/app/doctor/services";
    }

    @GetMapping("/history/{patientID}")
    public String showPatientHistory(@PathVariable long patientID, Model model, Principal principal) {
        Patient patient = patientRepository.findById(patientID).orElseThrow(PatientNotFound::new);
        model.addAttribute("patient", patient);
        Doctor doctor = getDoctor(principal);
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

    @GetMapping("/remove-appointment/{appID}/{patientID}")
    public String prepToDeleteVisit(@PathVariable Long appID, @PathVariable Long patientID, Model model) {
        Appointment appointment = appointmentRepository.findById(appID).orElseThrow(AppointmentNotFound::new);
        model.addAttribute("appointment", appointment);
        Patient patient = patientRepository.findById(patientID).orElseThrow(PatientNotFound::new);
        model.addAttribute("patient", patient.getFullName());

        return "/doctor/remove_appointment";
    }

    @PostMapping("/remove-appointment/{appID}/{patientID}")
    public String deleteVisit(@PathVariable long appID, @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            appointmentRepository.deleteById(appID);
        }

        return "redirect:/app/doctor/schedule/";
    }


    @GetMapping("/edit")
    public String prepToEditPD(Principal principal, Model model) {
        model.addAttribute("doctor", getDoctor(principal));

        return "/doctor/edit";
    }

    @PostMapping("/edit")
    public String updatePD(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        if(result.hasErrors()) {
            return "/doctor/edit";
        }
        doctorRepository.save(doctor);

        return "redirect:/app/doctor/dashboard";
    }

    private Doctor getDoctor(Principal principal) {
        String email = principal.getName();
        return doctorRepository.getByEmail(email);
    }
}

