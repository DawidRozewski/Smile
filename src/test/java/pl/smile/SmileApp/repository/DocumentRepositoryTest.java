package pl.smile.SmileApp.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Document;
import pl.smile.SmileApp.entity.Patient;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.smile.SmileApp.service.impl.ServiceHelper.*;

@DataJpaTest
class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    private Appointment appointment;
    private Appointment secondAppointment;
    private Patient patient;
    private Patient secondPatient;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = createDoctor();
        doctorRepository.save(doctor);

        patient = createPatient(doctor);
        patientRepository.save(patient);
        secondPatient = createPatient(doctor);
        patientRepository.save(secondPatient);

        appointment = createAppointment(patient, doctor);
        appointmentRepository.save(appointment);
        secondAppointment = createAppointment(secondPatient, doctor);
        appointmentRepository.save(secondAppointment);
    }

    @Test
    public void givenAppointmentIdAndPatientId_whenFindAllByAppointment_IdAndPatient_Id_thenReturnDocumentsList() {
        // given
        Document document = createDocument("tooth.jpg", patient, appointment);
        documentRepository.save(document);

        Document document1 = createDocument("teeth.jpg", patient, appointment);
        documentRepository.save(document1);

        Document document2 = createDocument("AnotherPatientDocument.txt", secondPatient, secondAppointment);
        documentRepository.save(document2);

        // when
        List<Document> documentsList = documentRepository.findAllByAppointment_IdAndPatient_Id(appointment.getId(), patient.getId());
        // then
        assertThat(documentsList).isNotNull();
        assertThat(documentsList.size()).isEqualTo(2);
        assertThat(documentsList.get(0).getPatient()).isEqualTo(patient);
        assertThat(documentsList.get(1).getPatient()).isEqualTo(patient);
    }

    private Document createDocument(String fileName, Patient patient, Appointment appointment) {
        byte[] data = new byte[1];
        return Document.builder()
                .name(fileName)
                .type("image/jpg")
                .data(data)
                .patient(patient)
                .appointment(appointment)
                .build();
    }
}