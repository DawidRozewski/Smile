package pl.smile.SmileApp.model;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ScheduledTasks {
    private final TwilioAcc twilioAcc;
    private final AppointmentRepository appointmentRepository;

    @Scheduled(cron = "0 00 07 * * *")
    public void sendSMS() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<Appointment> appointmentsThreeDaysBeforeVisit = getAllAppointmentsThreeDaysBeforeVisit(appointments);
        for (Appointment a : appointmentsThreeDaysBeforeVisit) {
            setTwilioConfig();
            String message = prepareMessage(appointmentsThreeDaysBeforeVisit);
            createMessage(message, a);
        }
    }

    private List<Appointment> getAllAppointmentsThreeDaysBeforeVisit(List<Appointment> appointments) {
        return appointments.stream()
                .filter(a -> !a.isFinished())
                .filter(a -> a.getDate().minusDays(3).equals(LocalDate.now()))
                .collect(Collectors.toList());
    }

    private void setTwilioConfig() {
        Twilio.init(twilioAcc.getTWILIO_ACCOUNT_SID(),
                twilioAcc.getTWILIO_AUTH_TOKEN());
    }

    private String prepareMessage(List<Appointment> appointmentsThreeDaysBeforeVisit) {
        String reminderMessage = "";
        for (Appointment a : appointmentsThreeDaysBeforeVisit) {
            reminderMessage = String.format("Hello %s !. Dr. %s here. " +
                            "Just wanted to send you a friendly reminder that you're scheduled to see me on date %s at %s ." +
                            "If you need to cancel your visit, call 789 024 803",
                    a.getPatient().getFirstName(),
                    a.getDoctor().getFullName(),
                    a.getDate(),
                    a.getTime()
            );
        }
        return reminderMessage;
    }

    private void createMessage(String message, Appointment a) {
        Message.creator(new PhoneNumber("+48" + a.getPatient().getPhoneNumber()),
                new PhoneNumber(twilioAcc.getTrialNumber()),
                message).create();
    }
}

