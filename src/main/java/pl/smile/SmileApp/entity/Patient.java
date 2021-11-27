package pl.smile.SmileApp.entity;
import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;
import pl.smile.SmileApp.validators.UniqueEmail;
import pl.smile.SmileApp.validators.UniquePesel;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
//@PasswordMatches
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

    @Transient
    private String repassword;

    @UniqueEmail
    @Email()
    @NotBlank
    private String email;

    @UniquePesel
    @PESEL()
    private String pesel;

    @Pattern(regexp = "^[5-8]\\d{8}$", message = "Podaj prawidlowy numer telefonu.")
    @NotBlank
    @Min(9)
    private String phoneNumber;

    @AssertTrue
    private boolean processingOfPersonalData;

    @ManyToOne
    private Doctor doctor;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    private final String ROLE = "ROLE_PATIENT";

}
