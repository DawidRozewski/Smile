//package pl.smile.SmileApp.validators;
//import org.springframework.stereotype.Component;
//import pl.smile.SmileApp.entity.Patient;
//import pl.smile.SmileApp.repository.PatientRepository;
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//@Component
//public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
//
//    private final PatientRepository patientRepository;
//
//    public UniqueEmailValidator(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }
//
//    @Override
//    public void initialize(UniqueEmail constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
//        Patient byEmail = patientRepository.getByEmail(email);
//        return byEmail != null;
//    }
//}
