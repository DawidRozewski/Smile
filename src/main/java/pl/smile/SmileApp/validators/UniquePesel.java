//package pl.smile.SmileApp.validators;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Constraint(validatedBy = UniquePeselValidator.class)
//@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.FIELD, ElementType.METHOD})
//public @interface UniquePesel {
//    String message() default "Podany pesel już istnieje.";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default{};
//}
