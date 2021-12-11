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

    @Scheduled(cron = "0 18 14 * * *")
    public void sendSMS() {
        List<Appointment> appointments = appointmentRepository.findAll();

        List<Appointment> list = appointments.stream()
                .filter(a -> !a.isFinished())
                .filter(a -> a.getDate().minusDays(3).equals(LocalDate.now()))
                .collect(Collectors.toList());

        for (Appointment a : list) {
            Twilio.init(
                    twilioAcc.getAccSid(),
                    twilioAcc.getAuthToken());
            Message.creator(new PhoneNumber("+48" + a.getPatient().getPhoneNumber()),
                    new PhoneNumber(twilioAcc.getTrialNumber()),
                    "Przypominamy, że za 3 dni masz wizytę w naszym gabiniecie SMILE o godzinie: " + a.getTime() +
                    ". W celu odwołania, prosimy o kontakt pod numerem: 665 432 147").create();

        }
    }
}
