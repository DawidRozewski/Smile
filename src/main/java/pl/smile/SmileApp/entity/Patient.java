package pl.smile.SmileApp.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;
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

    @NotBlank(message = "Pole nie moze byc puste.")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Wpisz poprawne imie.")
    private String firstName;

    @NotBlank(message = "Pole nie moze byc puste.")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Wpisz poprawne nazwisko.")
    private String lastName;

    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Haslo musi posiadac przynajmniej 1 duza litere, 1 cyfre, 1 znak specjalny oraz minimum 8 znakow dlugosci.")
    private String password;

    @Email(message = "Podany e-mail jest niepoprawny.")
    @Column(unique = true)
    private String email;

    @PESEL(message = "Podany pesel jest niepoprawny.")
    @Column(unique = true)
    private String pesel;

    @NotNull(message = "Pole nie moze byc puste.")
    private int phoneNumber;

    @AssertTrue(message = "Akceptacja zgody na przetwarzanie danych osobowych jest wymagana.")
    private boolean processingOfPD;

    @ManyToOne
    private Doctor doctor;

}
