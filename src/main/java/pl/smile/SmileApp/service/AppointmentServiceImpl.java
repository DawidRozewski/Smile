package pl.smile.SmileApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.repository.AppointmentRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public  class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<LocalTime> getAvailableHours(LocalDate appointmentDate) {
        List<LocalTime> workingHours = new ArrayList<>(Arrays.asList(
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

    @Override
    public void ifSundayThrowNotification(Appointment appointment, BindingResult result) {
        if(appointment.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            result.rejectValue("date", "error.appointment", "We are not working on Sundays :)");
        }
    }
    @Override
    public void ifConfirmedDeleteApp(long appID, String confirmed) {
        if ("yes".equals(confirmed)) {
            appointmentRepository.deleteById(appID);
        }
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


}
