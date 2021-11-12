package pl.smile.SmileApp.entity;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentSlot {
    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime start;
    private LocalDate end;

    @ManyToOne
    private Doctor doctor;
}
