package com.robot.constraints;

import javax.validation.Constraint;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.validation.Payload;

@Constraint(validatedBy = GenderValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface Gender {

	String message() default "{com.robot.constraints.Gender.message}";
	Class <?> [] groups() default {};
	Class <? extends Payload> [] payload() default {};
}
