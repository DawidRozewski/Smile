package pl.smile.SmileApp.entity;
import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;
import pl.smile.SmileApp.validators.UniqueEmail;
import pl.smile.SmileApp.validators.UniquePesel;

import javax.persistence.*;
import javax.validation.constraints.*;

@Builder
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]*$", message = "Enter a valid name.")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]*$", message = "Enter a valid last name.")
    private String lastName;

    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "The password must contain at least 8 characters, 1 uppercase, 1 digit and 1 special character.")
    private String password;

    @Transient
    private String repassword;

    @UniqueEmail
    @Email
    @NotBlank
    private String email;

    @UniquePesel
    @PESEL()
    private String pesel;

    @Pattern(regexp = "^[5-8]\\d{8}$", message = "Enter a valid phone number.")
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
