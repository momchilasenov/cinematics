package com.cinematics.dao;

import com.cinematics.model.movie.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

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
    catch (EmptyResultDataAccessException e) {
      log.debug(e.getCause());
      e.getStackTrace();
      return null;
    }
  }
}
