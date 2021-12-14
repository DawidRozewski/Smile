package pl.smile.SmileApp.controller.doctor;


import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.service.DoctorServiceImpl;
import pl.smile.SmileApp.service.PatientServiceImpl;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/app/doctor")
public class D_DashboardController {

    private final PatientServiceImpl patientService;
    private final DoctorServiceImpl doctorService;

    @GetMapping("/dashboard")
    public String showAllPatients(Principal principal, Model model, @Param("pesel") String pesel) {
        Doctor doctor = doctorService.getDoctor(principal);
        model.addAttribute("patients", patientService.listAll(pesel, doctor));

        return "/doctor/dashboard";
    }


}
