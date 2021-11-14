//package pl.smile.SmileApp.validators;
//
//import pl.smile.SmileApp.entity.Patient;
//import pl.smile.SmileApp.repository.PatientRepository;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
//
//    private final PatientRepository patientRepository;
//
//    public UniqueEmailValidator(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }
//
//    @Override
//    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
//        Patient patient = patientRepository.findByEmail(email);
//        if(patient == null) {
//            return true;
//        }
//        return patient.getEmail().equals(email);
//
//    }
//
//}
