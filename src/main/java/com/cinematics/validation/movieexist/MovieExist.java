package com.cinematics.validation.movieexist;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD, PARAMETER})
public @interface MovieExist
{
  String name();

  String message() default "Movie already exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
