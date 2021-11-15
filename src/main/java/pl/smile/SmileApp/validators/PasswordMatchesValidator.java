//package pl.smile.SmileApp.validators;
//
//import pl.smile.SmileApp.entity.Patient;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
//    @Override
//    public void initialize(PasswordMatches constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
//        Patient patient = (Patient) object;
//        return patient.getPassword().equals(patient.getRepassword());
//    }
//
//}
