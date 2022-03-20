package pl.smile.SmileApp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.service.AppointmentService;
import pl.smile.SmileApp.service.ErrorMessageHandling;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService, ErrorMessageHandling {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<LocalTime> getAvailableHours(LocalDate appointmentDate) {
        List<LocalTime> workingHours = getWorkingHours();
        List<Appointment> allByDate = getAppointmentsByDay(appointmentDate);
        List<LocalTime> reservedHours = getReservedHours(allByDate);
        removeReservedHoursFromAppointmentDay(workingHours, reservedHours);
        return workingHours;
    }

    private List<LocalTime> getWorkingHours() {
        List<LocalTime> workingHours = new ArrayList<>();
        for (int hour = 7; hour <= 15; hour++) {
            setWorkingHours(workingHours, hour);
        }
        return workingHours;
    }

    private void setWorkingHours(List<LocalTime> workingHours, int hour) {
        workingHours.add(LocalTime.of(hour, 0));
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

    private void removeReservedHoursFromAppointmentDay(List<LocalTime> workingHours, List<LocalTime> reservedHours) {
        workingHours.removeAll(reservedHours);
    }

    @Override
    public boolean isBookedDayIsSunday(Appointment appointment, BindingResult result) {
        if (isSunday(appointment)) {
            setErrorMessageToView(result);
            return true;
        }
        return false;
    }

    private boolean isSunday(Appointment appointment) {
        return appointment.getDate().getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    @Override
    public void setErrorMessageToView(BindingResult result) {
        result.rejectValue("date", "error.appointment", "We are not working on Sundays :)");
    }

    @Override
    public void ifConfirmedDeleteApp(long appID, String decision) {
        if (isConfirmed(decision)) {
            appointmentRepository.deleteById(appID);
        }
    }

    private boolean isConfirmed(String decision) {
        return "yes".equals(decision);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getById(long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Appointment", id));
    }

    @Override
    public List<Appointment> getAllActiveAppointments(long doctorId) {
        return appointmentRepository.findAllActiveAppointments(doctorId);
    }

    @Override
    public List<Appointment> getFutureOrPresentPatientApp(long patientID, long id, LocalDate now) {
        return appointmentRepository.getFutureOrPresentPatientApp(patientID, id, now);
    }


    @Override
    public List<Appointment> getPatientHistoryApp(long patientID, long id) {
        return appointmentRepository.getPatientHistoryApp(patientID, id);
    }

    @Override
    public List<Appointment> getFutureOrPresentAppointments(long id, LocalDate now) {
        return appointmentRepository.getFutureOrPresentAppointments(id, now);
    }

    @Override
    public List<Appointment> getPastAppointments(long id) {
        return appointmentRepository.getPastAppointments(id);
    }


}
