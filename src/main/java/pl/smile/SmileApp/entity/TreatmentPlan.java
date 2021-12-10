package pl.smile.SmileApp.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

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
    private int visitNumber;

    @FutureOrPresent
    private LocalDate visitDate;

    @NotBlank
    private String description;

    @NotNull
    @Min(value = 0, message = "Cena nie może być poniżej 0.")
    private int price;

    @OneToOne
    private Patient patient;

    @OneToOne
    private Doctor doctor;

}
