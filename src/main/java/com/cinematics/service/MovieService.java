package com.cinematics.service;

import com.cinematics.dao.MovieDao;
import com.cinematics.exception.MovieException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieService
{
  private final MovieDao movieDao;

  public void createMovie(String name, String director)
  {
    int result = movieDao.createMovie(name, director);

    if (result == 0) {
      throw new MovieException("Error creating movie: " + name);
    }

    log.info("Created {} movie: {} by {}", result, name, director);
  }
}
