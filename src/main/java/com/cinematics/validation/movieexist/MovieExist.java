package com.cinematics.validation.movieexist;

import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({METHOD, PARAMETER})
public @interface MovieExist
{
  String name();

  String message() default "Movie already exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
