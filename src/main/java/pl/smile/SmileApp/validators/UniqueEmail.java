//package pl.smile.SmileApp.validators;
//
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Constraint(validatedBy = UniqueEmailValidator.class)
//@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.METHOD, ElementType.FIELD})
//
//public @interface UniqueEmail {
//
//     String message() default "Podany adres e-mail już istnieje.";
//
//     Class<?>[] groups() default {};
//
//     Class<? extends Payload>[] payload() default{};
//}
