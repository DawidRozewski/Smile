package pl.smile.SmileApp.service.impl;

import pl.smile.SmileApp.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;


public class ServiceHelper {

    public static Doctor createDoctor() {
        return Doctor.builder()
                .firstName("Doctor")
                .lastName("Ambrozii")
                .password("test")
                .repassword("test")
                .phoneNumber("789024803")
                .build();
    }

   public static Patient createPatient(Doctor doctor) {
        return  Patient.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .pesel("87021318486")
                .phoneNumber("789024803")
                .email("averoza@o2.pl")
                .password("test")
                .repassword("test")
                .processingOfPersonalData(true)
                .doctor(doctor)
                .build();
    }
    public static TreatmentPlan createTreatmentPlan(Doctor doctor, Patient patient, String description) {
        return  TreatmentPlan.builder()
                .patient(patient)
                .doctor(doctor)
                .description(description)
                .price(100)
                .visitNumber(1)
                .visitDate(LocalDate.of(2022,3,19))
                .build();
    }

    static DentalTreatment createDentalTreatment() {
        return  DentalTreatment.builder()
                .amount(100)
                .description("Description test")
                .RTG("Yes")
                .build();
    }

    public static Appointment createAppointment(Patient patient, Doctor doctor) {
        return Appointment.builder()
                .date(LocalDate.of(2022,3,19))
                .time(LocalTime.of(8,0))
                .serviceDescription("description")
                .doctor(doctor)
                .patient(patient)
                .price(100)
                .isFinished(false)
                .build();
    }
}
