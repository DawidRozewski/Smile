package pl.smile.SmileApp.controller.doctor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.DentalTreatment;
import pl.smile.SmileApp.service.DentalTreatmentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/app/doctor")
public class D_DentalServicesController {

    private final DentalTreatmentService dentalTreatmentService;

    @GetMapping("/services")
    public String prepToAddService(Model model) {
        model.addAttribute("service", new DentalTreatment());
        return "/doctor/services";
    }

    @PostMapping("/services")
    public String addService(@ModelAttribute("service") @Valid DentalTreatment dentalTreatment, BindingResult result) {
        if (result.hasErrors()) {
            return "/doctor/services";
        }

        dentalTreatmentService.save(dentalTreatment);
        return "redirect:/app/doctor/services";
    }

    @GetMapping("/edit-service/{id}")
    public String prepToEditService(@PathVariable long id, Model model) {
        DentalTreatment dentalTreatment = dentalTreatmentService.getById(id);
        model.addAttribute("service", dentalTreatment);
        return "/doctor/services";
    }

    @PostMapping("/edit-service/{id}")
    public String editService(@ModelAttribute("service") @Valid DentalTreatment dentalTreatment, BindingResult result) {
        if (result.hasErrors()) {
            return "/doctor/services";
        }

        dentalTreatmentService.save(dentalTreatment);
        return "redirect:/app/doctor/services";
    }

    @GetMapping("/remove-service/{id}")
    public String prepToDeleteService(@PathVariable long id, Model model) {
        DentalTreatment dentalTreatment = dentalTreatmentService.getById(id);
        model.addAttribute("service", dentalTreatment);
        return "/doctor/remove_service";
    }

    @PostMapping("/remove-service/{id}")
    public String deleteService(@PathVariable long id, @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            dentalTreatmentService.delete(id);
        }

        return "redirect:/app/doctor/services";
    }

    @ModelAttribute("services")
    public List<DentalTreatment> servicesList() {
        return dentalTreatmentService.getAll();
    }


}
