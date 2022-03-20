package pl.smile.SmileApp.service;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService{
    boolean isBookedDayIsSunday(Appointment appointment, BindingResult result);
    void ifConfirmedDeleteApp(long appID, String confirmed);
    Appointment save(Appointment appointment);
    Appointment getById(long id);
    List<LocalTime> getAvailableHours(LocalDate appointmentDate);
    List<Appointment> getAllActiveAppointments(long id);
    List<Appointment> getFutureOrPresentPatientApp(long patientID, long id, LocalDate now);
    List<Appointment> getPatientHistoryApp(long patientID, long id);
    List<Appointment> getFutureOrPresentAppointments(long id, LocalDate now);
    List<Appointment> getPastAppointments(long id);

}
