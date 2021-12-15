package pl.smile.SmileApp.controller.homepage;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Admin;
import pl.smile.SmileApp.entity.DentalService;
import pl.smile.SmileApp.repository.AdminRepository;
import pl.smile.SmileApp.repository.ServiceRepository;
import java.util.List;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class HomeController {

    private final ServiceRepository serviceRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

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

    @GetMapping("/add-admin")
    @ResponseBody
    public String test() {
        Admin admin = new Admin();
        admin.setUsername("admin1");
        admin.setPassword(passwordEncoder.encode("admin123"));
        adminRepository.save(admin);

        return "Udalo sie zapisac admina";
    }


}






