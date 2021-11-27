package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class HomeController {

    private final AppointmentRepository appointmentRepository;

    @GetMapping("/check")
    @ResponseBody
    public String homePage() {
        LocalDate appDay = LocalDate.of(2021, 11, 30);
        List<LocalTime> workingHours = getAvailableHours(appDay);

        return workingHours.toString();
    }

    private List<LocalTime> getAvailableHours(LocalDate date) {
        List<LocalTime> workingHours = new ArrayList<>(List.of(
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0)
        ));

        List<Appointment> allByDate = appointmentRepository.findByDateAndFinishedIsFalse(date);
        List<LocalTime> reservedHours = allByDate
                .stream()
                .map(Appointment::getTime)
                .collect(Collectors.toList());

        workingHours.removeAll(reservedHours);
        return workingHours;
    }
}





