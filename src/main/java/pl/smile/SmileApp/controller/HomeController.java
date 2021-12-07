package pl.smile.SmileApp.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Service;
import pl.smile.SmileApp.model.TwilioAcc;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.repository.ServiceRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class HomeController {

    private final ServiceRepository serviceRepository;
    private final TwilioAcc twilioAcc;
    private final AppointmentRepository appointmentRepository;

    @GetMapping("")
    public String homePage() {
        return "homepage";
    }


    @GetMapping("/services")
    public String services() {
        return "/homepage/services";
    }

    @ModelAttribute("services")
    public List<Service> servicesList() {
        return serviceRepository.findAll();
    }


    @GetMapping
    @Scheduled(cron = "10 13 * * * *")
    public void sendSMS() {
        List<Appointment> appointments = appointmentRepository.findAll();

        List<Appointment> list = appointments.stream()
                .filter(a -> !a.isFinished())
                .filter(a -> a.getDate().minusDays(3).equals(LocalDate.now()))
                .filter(a -> a.getPatient().getPhoneNumber().equals("789024803"))
                .collect(Collectors.toList());

        for (Appointment a : list) {
            Twilio.init(
                    twilioAcc.getAccSid(),
                    twilioAcc.getAuthToken());
            Message.creator(new PhoneNumber("+48" + a.getPatient().getPhoneNumber()),
                    new PhoneNumber(twilioAcc.getTrialNumber()),
                    "Przypominamy, że za 3 dni wizyta. :) W celu odwołania wizyty, prosimy o kontakt pod numerem: 999 999 999").create();

        }
    }
}







