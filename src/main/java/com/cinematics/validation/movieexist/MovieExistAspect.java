package com.cinematics.validation.movieexist;

import com.cinematics.exception.MovieException;
import com.cinematics.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@Log4j2
@RequiredArgsConstructor
public class MovieExistAspect implements MethodInterceptor
{
  private final MovieService movieService;

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable
  {
    log.info("MovieExist Aspect started");

    Method method = invocation.getMethod();
    MovieExist movieExistAnnotation = method.getAnnotation(MovieExist.class);

    if (!isEmpty(movieExistAnnotation)) {
      String name = movieExistAnnotation.name();
      log.info("Calling getMovie for movie {}", name);

      if (null != movieService.getMovie(name)) {
        throw new MovieException("Movie " + name + " already exists");
      }
    }
    return invocation.proceed();
  }
}
