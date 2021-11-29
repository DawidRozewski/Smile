package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.service.AppointmentServiceImpl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class HomeController {


    private final AppointmentServiceImpl appointmentService;

    @GetMapping("/check")
    @ResponseBody
    public String homePage() {
        LocalDate appDay = LocalDate.of(2021, 11, 30);
        List<LocalTime> workingHours =  appointmentService.getAvailableHours(appDay);

        return workingHours.toString();
    }


}





