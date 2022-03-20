package pl.smile.SmileApp.service.impl;

import pl.smile.SmileApp.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceHelper {

    static Doctor createDoctor() {
        return Doctor.builder()
                .id(1L)
                .firstName("JanEDITED")
                .lastName("KowalskiEDITED")
                .password("test")
                .repassword("test")
                .phoneNumber("789024803")
                .build();
    }

    static Patient createPatient() {
        return  Patient.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .pesel("94102910617")
                .phoneNumber("789024803")
                .email("averoza@o2.pl")
                .password("test")
                .repassword("test")
                .processingOfPersonalData(true)
                .doctor(createDoctor())
                .build();
    }
    static TreatmentPlan createTreatmentPlan() {
        return  TreatmentPlan.builder()
                .id(1L)
                .patient(createPatient())
                .doctor(createDoctor())
                .description("description")
                .price(100)
                .visitNumber(1)
                .visitDate(LocalDate.of(2022,3,19))
                .build();
    }

    static DentalTreatment createDentalTreatment() {
        return  DentalTreatment.builder()
                .id(1L)
                .amount(100)
                .description("Description test")
                .RTG("Yes")
                .build();
    }

    static Appointment createAppointment() {
        return Appointment.builder()
                .id(1L)
                .date(LocalDate.now())
                .time(LocalTime.of(8,0))
                .serviceDescription("description")
                .price(100)
                .doctor(createDoctor())
                .patient(createPatient())
                .build();
    }
}
