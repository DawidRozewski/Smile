package pl.smile.SmileApp.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime end;

    @ManyToOne
    private Doctor doctor;

    private boolean isReserved;
}
