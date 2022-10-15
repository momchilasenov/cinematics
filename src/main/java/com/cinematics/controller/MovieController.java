package com.cinematics.controller;

import com.cinematics.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController
{
  public final static String API_MOVIE = "/api/be/movie";

  private final MovieService movieService;

  @PostMapping(API_MOVIE)
  @ResponseStatus(HttpStatus.CREATED)
  public void createMovie(@RequestParam String name,
                          @RequestParam String director)
  {
    movieService.createMovie(name, director);
  }
}
