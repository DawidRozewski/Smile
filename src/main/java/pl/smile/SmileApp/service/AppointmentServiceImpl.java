package pl.smile.SmileApp.service;

import org.springframework.stereotype.Service;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<LocalTime> getAvailableHours(LocalDate appointmentDate) {
        List<LocalTime> workingHours = new ArrayList<>(List.of(
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0)
        ));

        List<Appointment> allByDate = appointmentRepository.findByDateAndFinishedIsFalse(appointmentDate);
        List<LocalTime> reservedHours = allByDate
                .stream()
                .map(Appointment::getTime)
                .collect(Collectors.toList());

        workingHours.removeAll(reservedHours);
        return workingHours;
    }
}
