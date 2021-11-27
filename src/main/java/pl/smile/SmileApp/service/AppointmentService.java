package pl.smile.SmileApp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {

    List<LocalTime> getAvailableHours(LocalDate appointmentDate);
}
