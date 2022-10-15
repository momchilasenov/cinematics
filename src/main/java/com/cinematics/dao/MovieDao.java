package com.cinematics.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
}
