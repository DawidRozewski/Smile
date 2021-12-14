package pl.smile.SmileApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.smile.SmileApp.service.AppointmentServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/app/get-appointment-hours")
@RequiredArgsConstructor
public class AppointmentRestController {

    private final AppointmentServiceImpl appointmentService;

    @GetMapping("/available")
    public List<LocalTime> getAvailable(@RequestParam("date") String date) {
        LocalDate pickedDate = LocalDate.parse(date);

        return appointmentService.getAvailableHours(pickedDate);
    }

}
