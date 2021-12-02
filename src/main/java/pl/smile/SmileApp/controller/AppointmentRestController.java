package pl.smile.SmileApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.smile.SmileApp.service.AppointmentServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * KONIECZNIE do tego typu rzeczy musi być osobny kontroler który jest @RestController
 * W tamtych kontrolerach zwrcasza jako wyniki metod nazwy plikow, ewentualnie zwykly tekst jak dodamy @ResponseBody
 * Tutaj jest konieczne zwracanie danych w formacie JSON i tylko w ten sposób będzie możliwe to wykonanie w najłatwiejszy sposób.
 * To tutaj zmienia się troche postrzeganie kontrolera czyli REST KONTROLERA zwracajacego tylko dane ww JSON a nie rzeczy zwiazane z widokami
 * */


@RestController
@RequestMapping("/app/example-appointment")
@RequiredArgsConstructor
public class AppointmentRestController {

    private final AppointmentServiceImpl appointmentService;

    @GetMapping("/available")
    public List<LocalTime> getAvailable() {
        return appointmentService.getAvailableHours(LocalDate.now());
    }

}
