package pl.smile.SmileApp.controller.doctor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.DentalService;
import pl.smile.SmileApp.exceptions.ServiceNotFound;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.repository.ServiceRepository;
import pl.smile.SmileApp.repository.TreatmentPlanRepository;
import pl.smile.SmileApp.service.DoctorServiceImpl;
import pl.smile.SmileApp.service.PatientServiceImpl;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/app/doctor")
public class D_DentalServicesController {

    private final PatientServiceImpl patientService;
    private final DoctorServiceImpl doctorService;
    private final AppointmentRepository appointmentRepository;
    private final TreatmentPlanRepository treatmentRepository;
    private final ServiceRepository serviceRepository;



    @GetMapping("/services")
    public String prepToAddService(Model model) {
        model.addAttribute("service", new DentalService());
        model.addAttribute("services", serviceRepository.findAll());

        return "/doctor/services";
    }

    @PostMapping("/services")
    public String addService(@ModelAttribute("service") @Valid DentalService dentalService, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/app/doctor/services";
        }
        serviceRepository.save(dentalService);

        return "redirect:/app/doctor/services";
    }

    @GetMapping("/edit-service/{id}")
    public String prepToEditService(@PathVariable long id, Model model) {
        DentalService dentalService = serviceRepository.findById(id).orElseThrow(ServiceNotFound::new);
        model.addAttribute("service", dentalService);
        model.addAttribute("services", serviceRepository.findAll());

        return "/doctor/services";
    }

    @PostMapping("/edit-service/{id}")
    public String editService(@ModelAttribute("service") @Valid DentalService dentalService, BindingResult result) {
        if (result.hasErrors()) {
            return "/doctor/services";
        }
        serviceRepository.save(dentalService);

        return "redirect:/app/doctor/services";
    }

    @GetMapping("/remove-service/{id}")
    public String prepToDeleteService(@PathVariable long id, Model model) {
        DentalService dentalService = serviceRepository.findById(id).orElseThrow(ServiceNotFound::new);
        model.addAttribute("service", dentalService);

        return "/doctor/remove_service";
    }

    @PostMapping("/remove-service/{id}")
    public String deleteService(@PathVariable long id, @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            serviceRepository.deleteById(id);
        }

        return "redirect:/app/doctor/services";
    }


}
