package pl.smile.SmileApp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.smile.SmileApp.service.impl.ServiceHelper.*;

@DataJpaTest
class AppointmentRepositoryTest {

    public static final LocalDate TESTING_DATE = LocalDate.of(2022, 3, 21);
    public static final LocalDate MINUS_2_DAYS_FROM_TESTING_DATE = TESTING_DATE.minusDays(2);
    public static final LocalDate MINUS_3_DAYS_FROM_TESTING_DATE = TESTING_DATE.minusDays(3);
    public static final LocalDate PLUS_1_DAY_TO_TESTING_DATE = TESTING_DATE.plusDays(1);
    public static final LocalDate PLUS_2_DAYS_TO_TESTING_DATE = TESTING_DATE.plusDays(2);
    public static final boolean FINISHED = true;
    public static final boolean NOT_FINISHED = false;

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    private Doctor doctor;
    private Doctor secondDoctor;
    private Patient patient;
    private Patient secondPatient;

    @BeforeEach
    void setUp() {
        doctor = createDoctor();
        patient = createPatient("01232895118", "kowalski@gmail.com", doctor);
        doctorRepository.save(doctor);
        patientRepository.save(patient);

        secondDoctor = createDoctor();
        secondPatient = createPatient("04241233348", "liaaski@gmail.com", secondDoctor);
        doctorRepository.save(secondDoctor);
        patientRepository.save(secondPatient);

    }

    @Test
    public void givenPatientIdAndTodayDate_whenGetFutureOrPresentAppointments_thenReturnListOfFutureOrPresentAppointmentsInOrderByASC() {
        // given
        createAndSaveFutureAppointment(patient, doctor, PLUS_1_DAY_TO_TESTING_DATE);
        createAndSaveFutureAppointment(patient, doctor, PLUS_2_DAYS_TO_TESTING_DATE);
        createAndSavePastAppointment(patient, doctor, MINUS_2_DAYS_FROM_TESTING_DATE, NOT_FINISHED);

        // when
        List<Appointment> futureOrPresentAppointments = appointmentRepository.getFutureOrPresentAppointments(patient.getId(), TESTING_DATE);
        // then
        assertThat(futureOrPresentAppointments).isNotNull();
        assertThat(futureOrPresentAppointments.size()).isEqualTo(2);
        assertThat(futureOrPresentAppointments.get(0).getDate()).isEqualTo(PLUS_1_DAY_TO_TESTING_DATE);

    }

    @Test
    public void givenPatientId_whenGetPastAppointment_thenReturnListOfPatientPastAppointmentsInOrderDESC() {
        // given
        createAndSaveFutureAppointment(patient, doctor, PLUS_1_DAY_TO_TESTING_DATE);
        createAndSavePastAppointment(patient, doctor, MINUS_2_DAYS_FROM_TESTING_DATE, NOT_FINISHED);
        createAndSavePastAppointment(patient, doctor, MINUS_2_DAYS_FROM_TESTING_DATE, FINISHED);
        createAndSavePastAppointment(patient, doctor, MINUS_3_DAYS_FROM_TESTING_DATE, FINISHED);
        // when
        List<Appointment> pastAppointments = appointmentRepository.getPastAppointments(patient.getId());
        // then
        assertThat(pastAppointments).isNotNull();
        assertThat(pastAppointments.size()).isEqualTo(2);
        assertThat(pastAppointments.get(0).getDate()).isEqualTo(MINUS_2_DAYS_FROM_TESTING_DATE);
        assertThat(pastAppointments.get(1).getDate()).isEqualTo(MINUS_3_DAYS_FROM_TESTING_DATE);
    }

    @Test
    public void givenPatientIdAndDoctorIdAndTodayDate_whenGetFutureOrPresentPatientApp_thenReturnListOfPatientAppointmentsInOrderASC() {
        // given
        createAndSaveFutureAppointment(patient, doctor, TESTING_DATE);
        createAndSaveFutureAppointment(patient, doctor, PLUS_2_DAYS_TO_TESTING_DATE);
        createAndSavePastAppointment(patient, doctor, MINUS_2_DAYS_FROM_TESTING_DATE, FINISHED);
        createAndSaveFutureAppointment(secondPatient, secondDoctor, PLUS_2_DAYS_TO_TESTING_DATE);
        // when
        List<Appointment> futureOrPresentPatientApp = appointmentRepository.getFutureOrPresentPatientApp(patient.getId(), doctor.getId(), TESTING_DATE);
        // then
        assertThat(futureOrPresentPatientApp).isNotNull();
        assertThat(futureOrPresentPatientApp.size()).isEqualTo(2);
        assertThat(futureOrPresentPatientApp.get(0).getDate()).isEqualTo(TESTING_DATE);
    }

    @Test
    public void givenDoctorIdAndPatientId_whenGetPatientHistoryApp_thenReturnAppointmentListByPatientAndDoctor() {
        // given
        createAndSavePastAppointment(patient, secondDoctor, MINUS_2_DAYS_FROM_TESTING_DATE, FINISHED);
        createAndSavePastAppointment(secondPatient, doctor, MINUS_2_DAYS_FROM_TESTING_DATE, FINISHED);
        createAndSavePastAppointment(patient, doctor, MINUS_2_DAYS_FROM_TESTING_DATE, FINISHED);
        createAndSavePastAppointment(patient, doctor, MINUS_3_DAYS_FROM_TESTING_DATE, FINISHED);
        // when
        List<Appointment> patientHistoryApp = appointmentRepository.getPatientHistoryApp(patient.getId(), doctor.getId());
        // then
        assertThat(patientHistoryApp.size()).isEqualTo(2);
        assertThat(patientHistoryApp.get(0).getDate()).isEqualTo(MINUS_2_DAYS_FROM_TESTING_DATE);
        assertThat(patientHistoryApp.get(1).getDate()).isEqualTo(MINUS_3_DAYS_FROM_TESTING_DATE);
    }

    @Test
    public void givenAppointmentList_whenFindByDateAndFinishedIsFalse_thenReturnListOfAppointmentsByDateAndNotFinished() {
        // given
        createAndSavePastAppointment(patient, doctor, TESTING_DATE, NOT_FINISHED);
        createAndSavePastAppointment(patient, doctor, TESTING_DATE, FINISHED);
        // when
        List<Appointment> byDateAndFinishedIsFalse = appointmentRepository.findByDateAndFinishedIsFalse(TESTING_DATE);
        // then
        assertThat(byDateAndFinishedIsFalse).isNotNull();
        assertThat(byDateAndFinishedIsFalse.size()).isEqualTo(1);
        assertThat(byDateAndFinishedIsFalse.get(0).isFinished()).isFalse();

    }


    private Patient createPatient(String pesel, String email, Doctor doctor) {
        return Patient.builder()
                .firstName("Second")
                .lastName("Patient")
                .pesel(pesel)
                .phoneNumber("789024803")
                .email(email)
                .password("test")
                .repassword("test")
                .processingOfPersonalData(true)
                .doctor(doctor)
                .build();
    }

    private void createAndSaveFutureAppointment(Patient patient, Doctor doctor, LocalDate appointmentDay) {
        Appointment futureAppointment = createAppointment(patient, doctor);
        futureAppointment.setDoctor(doctor);
        futureAppointment.setPatient(patient);
        futureAppointment.setDate(appointmentDay);
        appointmentRepository.save(futureAppointment);
    }

    private void createAndSavePastAppointment(Patient patient, Doctor doctor, LocalDate appointmentDay, boolean isFinished) {
        Appointment pastAppointment = createAppointment(patient, doctor);
        pastAppointment.setDoctor(doctor);
        pastAppointment.setPatient(patient);
        pastAppointment.setFinished(isFinished);
        pastAppointment.setDate(appointmentDay);
        appointmentRepository.save(pastAppointment);
    }
}