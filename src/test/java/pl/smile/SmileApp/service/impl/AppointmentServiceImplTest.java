package pl.smile.SmileApp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static pl.smile.SmileApp.service.impl.ServiceHelper.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {
    public static final LocalDate TODAY = LocalDate.now();
    public static final long APPOINTMENT_ID = 0L;
    public static final String CONFIRMED_BY_USER = "yes";

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;
    private Patient patient;
    private Doctor doctor;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        doctor = createDoctor();
        patient = createPatient(doctor);
        appointment = createAppointment(patient, doctor);
    }

    @Test
    public void givenAppointmentDate_whenGetAvailableHours_thenReturnListOfAvailableHoursForBooking() {
        // given
        LocalDate bookingDay = TODAY;
        List<Appointment> bookedAppointments = new ArrayList<>();
        bookedAppointments.add(appointment);
        given(appointmentRepository.findByDateAndFinishedIsFalse(bookingDay)).willReturn(bookedAppointments);
        // when
        List<LocalTime> availableHoursForBooking = appointmentService.getAvailableHours(bookingDay);
        // then
        assertThat(availableHoursForBooking).isNotNull();
        assertThat(availableHoursForBooking.size()).isEqualTo(8);

    }

    @Test
    public void givenAppointmentIdAndConfirmationByUser_whenIfConfirmedDeleteApp_thenDeleteAppointment() {
        // given
        willDoNothing().given(appointmentRepository).deleteById(APPOINTMENT_ID);
        // when
        appointmentService.ifConfirmedDeleteApp(APPOINTMENT_ID, CONFIRMED_BY_USER);
        // then
        verify(appointmentRepository, times(1)).deleteById(APPOINTMENT_ID);
    }

    @Test
    public void givenAppointment_whenSave_thenReturnAppointmentObject() {
        // given
        given(appointmentRepository.save(appointment)).willReturn(appointment);
        // when
        Appointment savedAppointment = appointmentService.save(appointment);
        // then
        assertThat(savedAppointment).isNotNull();
        assertThat(savedAppointment.getId()).isEqualTo(APPOINTMENT_ID);
    }

    @Test
    public void givenAppointmentId_whenGetById_thenReturnAppointmentObject() {
        // given
        given(appointmentRepository.findById(APPOINTMENT_ID)).willReturn(Optional.of(appointment));
        // when
        Appointment appointmentById = appointmentService.getById(APPOINTMENT_ID);
        // then
        assertThat(appointmentById).isNotNull();
        assertThat(appointmentById.getId()).isEqualTo(APPOINTMENT_ID);
    }

    @Test
    public void givenBookingDayIsNotSunday_whenIsBookedDayIsSunday_thenReturnFalse() {
        // given
        appointment.setDate(LocalDate.of(2022,3,19));
        // when
        boolean result = appointmentService.isBookedDayIsSunday(appointment, bindingResult);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void givenBookingDayIsSunday_whenIsBookedDayIsSunday_thenReturnFalse() {
        // given
        appointment.setDate(LocalDate.of(2022,3,20));
        // when
        boolean result = appointmentService.isBookedDayIsSunday(appointment, bindingResult);
        // then
        assertThat(result).isTrue();
    }


}