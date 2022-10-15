package com.cinematics.model.movie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie
{
  private String name;
  private String director;

}
