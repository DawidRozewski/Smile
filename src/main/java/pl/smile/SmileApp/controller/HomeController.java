package pl.smile.SmileApp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/app")
public class HomeController {

    @GetMapping("")
    public String showHomePage() {
            return "homepage";
    }

    @GetMapping("/appointment")
    @Secured({"ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_PATIENT"})
    @ResponseBody
    public String prepToAppointment() {
        return "witam pacjenta";
    }

}
