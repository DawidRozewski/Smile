package pl.smile.SmileApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/patient/dashboard";
    }


    @GetMapping("/history")
    public String history() {
        return "/patient/history";
    }

    @GetMapping("/services")
    public String services() {
        return "/patient/services";
    }

    @GetMapping("/treatment")
    public String treatment() {
        return "/patient/treatment";
    }

    @GetMapping("/appointment")
    public String appointment() {
        return "/patient/appointment";
    }
}
