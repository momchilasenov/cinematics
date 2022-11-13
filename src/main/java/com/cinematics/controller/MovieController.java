package com.cinematics.controller;

import com.cinematics.model.movie.Movie;
import com.cinematics.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
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

  @GetMapping(API_MOVIE)
  @ResponseStatus(HttpStatus.OK)
  public Movie getMovie(@RequestParam String name)
  {
    return movieService.getMovie(name);
  }

    @PutMapping(API_MOVIE)
    @ResponseStatus(HttpStatus.OK)
    public String updateMovie(@RequestParam String oldMovie, @RequestBody Movie newMovie) {
      int result = movieService.updateMovie(oldMovie, newMovie);
      if(result<1){
          return "Error, movie not updated";
      }
      return "Movie updated";
    }
}
