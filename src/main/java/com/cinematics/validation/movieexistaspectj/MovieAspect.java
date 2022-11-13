package com.cinematics.validation.movieexistaspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class MovieAspect
{
  @Before("onMovieCreation()")
  public void MovieAdvice(JoinPoint joinPoint)
  {
    Object[] args = joinPoint.getArgs();
    if (args[0].equals("Titanic")) {
      System.out.println("YEAAAAAHHHHHH");
    }
  }

  @Pointcut("execution(* createMovie())")
  public void onMovieCreation()
  {
  }
}
