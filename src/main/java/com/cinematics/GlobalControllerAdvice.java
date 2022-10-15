package com.cinematics;

import com.cinematics.exception.MovieException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class GlobalControllerAdvice
{
  @ExceptionHandler(MovieException.class)
  public ResponseEntity<Map<String, String>> handleMovieException(MovieException exception)
  {
    Map<String, String> errors = getErrorDetails(exception.getMessage());

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  private Map<String, String> getErrorDetails(String message)
  {
    return new HashMap<String, String>()
    {{
      put("timestamp", LocalDateTime.now().toString());
      put("message", message);
    }};
  }
}
