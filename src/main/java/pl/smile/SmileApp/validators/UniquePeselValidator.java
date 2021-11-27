package pl.smile.SmileApp.validators;
import pl.smile.SmileApp.repository.PatientRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePeselValidator implements ConstraintValidator<UniquePesel, String> {

    private final PatientRepository patientRepository;

    public UniquePeselValidator(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext constraintValidatorContext) {
        return patientRepository.findByPesel(pesel) == null;
    }
}
