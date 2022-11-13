package com.cinematics.dao;

import com.cinematics.model.movie.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class MovieDao
{
  private final NamedParameterJdbcTemplate namedTemplate;

  public int createMovie(String name, String director)
  {
    String sql = ""
        + " INSERT INTO movie ( "
        + "   name,             "
        + "   director          "
        + " ) VALUES (          "
        + "   :name,            "
        + "   :director         "
        + " )                   ";

    MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("name", name)
        .addValue("director", director);

    log.debug(" {} executed with params: {} ", sql, params);

    return namedTemplate.update(sql, params);
  }

  public int updateMovie(String oldMovie, Movie newMovie)
  {
    String sql = ""
        + " UPDATE movie             "
        + " SET name = :name,        "
        + " director = :director     "
        + " WHERE name = :oldMovie   ";

    MapSqlParameterSource param = new MapSqlParameterSource()
        .addValue("name", newMovie.getName())
        .addValue("director", newMovie.getDirector())
        .addValue("oldMovie", oldMovie);

    return namedTemplate.update(sql, param);
  }

  public Movie getMovie(String name)
  {
    String sql = ""
        + " SELECT name, director "
        + " FROM movie            "
        + "   WHERE name = :name  "
        + "   AND ROWNUM < 2      ";

    MapSqlParameterSource param = new MapSqlParameterSource("name", name);

    try {
      return namedTemplate.queryForObject(sql, param, (rs, rownum) ->
          Movie.builder()
              .name(rs.getString("name"))
              .director(rs.getString("director"))
              .build()
      );
    }
    catch (IncorrectResultSizeDataAccessException e) {
      log.debug(e.getCause());
      e.getStackTrace();
      return null;
    }
  }

  public int deleteMovie(String movie)
  {
    String sql = ""
        + " DELETE FROM movie "
        + " WHERE name = :movie ";

    MapSqlParameterSource param = new MapSqlParameterSource("movie", movie);

    return namedTemplate.update(sql, param);
  }
}
