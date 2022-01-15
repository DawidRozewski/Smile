package pl.smile.SmileApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

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

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^[5-8]\\d{8}$", message = "Enter a valid phone number.")
    @NotBlank
    @Min(9)
    private String phoneNumber;

    private final String ROLE = "ROLE_DOCTOR";


    public String getFullName() {
        return firstName + " " + lastName;
    }

}
