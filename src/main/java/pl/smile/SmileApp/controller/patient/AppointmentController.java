package pl.smile.SmileApp.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @GetMapping("")
    public String isLogged(HttpSession session) {
        if (session.getAttribute("patient") == null) {
            return "redirect:/login";
        } else {
            return "redirect:/login";
        }
    }
}
