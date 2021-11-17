package pl.smile.SmileApp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Future
    private LocalDateTime start;

    @Future
    private LocalDateTime end;

    @NotBlank
    private String treatmentDescription;

    @NotNull
    private int price;

    @OneToOne
    private AppointmentSlot appointmentSlot;

    @ManyToOne
    private Patient patient;

}
