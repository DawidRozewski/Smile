package pl.smile.SmileApp.validators;
import lombok.AllArgsConstructor;
import pl.smile.SmileApp.repository.PatientRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniquePeselValidator implements ConstraintValidator<UniquePesel, String> {

    private final PatientRepository patientRepository;

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext constraintValidatorContext) {
        return patientRepository.findByPesel(pesel) == null;
    }
}
