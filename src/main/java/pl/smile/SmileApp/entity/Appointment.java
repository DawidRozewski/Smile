package pl.smile.SmileApp.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @FutureOrPresent
    private LocalDate date;

    private LocalTime time;

    @NotBlank
    private String serviceDescription;

    @NotNull
    private int price;

    boolean isFinished;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;


}
