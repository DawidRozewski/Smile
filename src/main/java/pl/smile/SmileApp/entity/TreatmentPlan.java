package pl.smile.SmileApp.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Min(value = 1, message = "Min. value 1.")
    private int visitNumber;

    @FutureOrPresent
    private LocalDate visitDate;

    @NotBlank
    private String description;

    @NotNull
    @Min(value = 0, message = "The price cannot be below zero.")
    private int price;

    @OneToOne
    private Patient patient;

    @OneToOne
    private Doctor doctor;

}
