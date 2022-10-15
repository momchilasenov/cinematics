package com.cinematics.model.actor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Actor
{
  private String firstName;
  private String lastName;
}
