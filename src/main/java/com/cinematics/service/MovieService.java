package com.cinematics.service;

import com.cinematics.dao.MovieDao;
import com.cinematics.exception.MovieException;
import com.cinematics.model.movie.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieService
{
  private final MovieDao movieDao;

  public void createMovie(String name, String director)
  {
    //assertMovieDoesNotExist(name);

    int result = movieDao.createMovie(name, director);

    log.info("Created {} movie: {} by {}", result, name, director);
  }

  public int updateMovie(String oldMovie, Movie newMovie)
  {
    return movieDao.updateMovie(oldMovie, newMovie);
  }

  @Cacheable(cacheNames = "movies", key="#name")
  public Movie getMovie(String name)
  {
    return movieDao.getMovie(name);
  }

  private void assertMovieDoesNotExist(String name)
  {
    Movie movie = getMovie(name);
    if (null != movie) {
      throw new MovieException("Movie " + name + " already exists");
    }
  }
}
