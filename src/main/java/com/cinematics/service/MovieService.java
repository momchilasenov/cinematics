package com.cinematics.service;

import com.cinematics.dao.MovieDao;
import com.cinematics.exception.MovieException;
import com.cinematics.model.movie.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieService
{
  private final MovieDao movieDao;

  @CachePut(cacheNames = "movies", key = "#name")
  public void createMovie(String name, String director)
  {
    assertMovieDoesNotExist(name);
    log.info("calling movieDao CREATE");
    int result = movieDao.createMovie(name, director);
    log.info("Created {} movie: {} by {}", result, name, director);
  }

  @CachePut(cacheNames = "movies", key = "#oldMovie")
  public int updateMovie(String oldMovie, Movie newMovie)
  {
    log.info("calling movieDao UPDATE");
    return movieDao.updateMovie(oldMovie, newMovie);
  }

  @Cacheable(cacheNames = "movies", key = "#name")
  public Movie getMovie(String name)
  {
    log.info("calling movieDao GET");
    return movieDao.getMovie(name);
  }

  @CacheEvict(cacheNames = "movies", key = "#movie")
  public int deleteMovie(String movie)
  {
    log.info("calling movieDao DELETE");
    return movieDao.deleteMovie(movie);
  }

  private void assertMovieDoesNotExist(String name)
  {
    Movie movie = getMovie(name);
    if (null != movie) {
      throw new MovieException("Movie " + name + " already exists");
    }
  }
}
