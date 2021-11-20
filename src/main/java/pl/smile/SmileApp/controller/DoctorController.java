package pl.smile.SmileApp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/app/doctor")
public class DoctorController {


    @GetMapping("/dashboard")
    public String dashboard() {
        return "/doctor/dashboard";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "/doctor/schedule";
    }


    @GetMapping("/patients")
    public String patients() {
        return "/doctor/patients";
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
