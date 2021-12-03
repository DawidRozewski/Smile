package pl.smile.SmileApp.controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Service;
import pl.smile.SmileApp.repository.ServiceRepository;

import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class HomeController {

    private final ServiceRepository serviceRepository;

    @GetMapping("")
    public String homePage() {
        return "homepage";
    }


    @GetMapping("/services")
    public String services() {
        return "/patient/services";
    }

    @ModelAttribute("services")
    public List<Service> servicesList() {
        return serviceRepository.findAll();
    }





}





