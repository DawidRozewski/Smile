package pl.smile.SmileApp.controller.homepage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.DentalService;
import pl.smile.SmileApp.repository.ServiceRepository;
import java.util.List;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class HomeController {

    private final ServiceRepository serviceRepository;

    @GetMapping("")
    public String homePage() {
        return "/homepage/homepage";
    }

    @GetMapping("/services")
    public String services() {
        return "/homepage/services";
    }

    @GetMapping("/about")
    public String about() {
        return "/homepage/about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "/homepage/contact";
    }

    @ModelAttribute("services")
    public List<DentalService> servicesList() {
        return serviceRepository.findAll();
    }


 }






