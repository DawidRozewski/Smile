package pl.smile.SmileApp.entity;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    @Email
    @NotBlank
    private String email;

    @PESEL()
    private String pesel;

    @Pattern(regexp = "^[5-8]\\d{8}$", message = "Podaj prawidlowy numer telefonu.")
    @NotNull
    @Min(9)
    private String phoneNumber;

    @AssertTrue
    private boolean confirmed;

    @ManyToOne
    private Doctor doctor;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();


}
