package pl.smile.SmileApp.validators;
import lombok.AllArgsConstructor;

import pl.smile.SmileApp.repository.PatientRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final PatientRepository patientRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return  patientRepository.getByEmail(email) == null;
    }
}
