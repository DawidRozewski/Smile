package pl.smile.SmileApp.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;
import pl.smile.SmileApp.validators.UniqueEmail;
import pl.smile.SmileApp.validators.UniquePesel;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Getter
@Setter
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$", message = "Wpisz poprawne imie.")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$", message = "Wpisz poprawne nazwisko.")
    private String lastName;

    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Haslo musi posiadac przynajmniej 1 duza litere, 1 cyfre, 1 znak specjalny oraz minimum 8 znakow dlugosci.")
    private String password;

    @Email
    @UniqueEmail
    @NotEmpty
    @NotNull
    private String email;

    @PESEL
    @UniquePesel
    private String pesel;

    @NotNull //Dodac poprawne wyrazenie
    private int phoneNumber;

    @AssertTrue
    private boolean processingOfPD;

    @ManyToOne
    private Doctor doctor;

}
