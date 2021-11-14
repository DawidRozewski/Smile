//package pl.smile.SmileApp.validators;
//
//import pl.smile.SmileApp.entity.Patient;
//import pl.smile.SmileApp.repository.PatientRepository;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class UniquePeselValidator implements ConstraintValidator<UniquePesel, String> {
//
//    private final PatientRepository patientRepository;
//
//    public UniquePeselValidator(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }
//
//    @Override
//    public boolean isValid(String pesel, ConstraintValidatorContext constraintValidatorContext) {
//        Patient patient = patientRepository.findByPesel(pesel);
//        if (patient != null) {
//            return !patient.getPesel().equals(pesel);
//        }
//        return true;
//    }
//}
