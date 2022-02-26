package pl.smile.SmileApp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.service.AppointmentService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<LocalTime> getAvailableHours(LocalDate appointmentDate) {
        List<LocalTime> workingHours = getWorkingHours();
        List<Appointment> allByDate = getAppointmentsByDay(appointmentDate);
        List<LocalTime> reservedHours = getReservedHours(allByDate);
        removeReservedHours(workingHours, reservedHours);
        return workingHours;
    }

    private void removeReservedHours(List<LocalTime> workingHours, List<LocalTime> reservedHours) {
        workingHours.removeAll(reservedHours);
    }

    private List<LocalTime> getWorkingHours() {
        List<LocalTime> workingHours = new ArrayList<>();
        for (int hour = 7; hour <= 15; hour++) {
            workingHours.add(LocalTime.of(hour, 0));
        }
        return workingHours;
    }

    private List<Appointment> getAppointmentsByDay(LocalDate appointmentDate) {
        return appointmentRepository.findByDateAndFinishedIsFalse(appointmentDate);
    }

    private List<LocalTime> getReservedHours(List<Appointment> allByDate) {
        return allByDate
                .stream()
                .map(Appointment::getTime)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isBookedDayIsSunday(Appointment appointment, BindingResult result) {
        if (isSunday(appointment)) {
            result.rejectValue("date", "error.appointment", "We are not working on Sundays :)");
            return true;
        }
        return false;
    }

    private boolean isSunday(Appointment appointment) {
        return appointment.getDate().getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    @Override
    public void ifConfirmedDeleteApp(long appID, String confirmed) {
        if (isConfirmed(confirmed)) {
            appointmentRepository.deleteById(appID);
        }
    }

    private boolean isConfirmed(String confirmed) {
        return "yes".equals(confirmed);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


}
