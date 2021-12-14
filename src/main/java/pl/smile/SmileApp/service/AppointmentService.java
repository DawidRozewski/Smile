package pl.smile.SmileApp.service;



import pl.smile.SmileApp.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService{

    List<LocalTime> getAvailableHours(LocalDate appointmentDate);
    Appointment save(Appointment appointment);
}
