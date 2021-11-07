package com.mattmerr.beets.util;

import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class UtilD4J {
  
  public static Optional<String> asString(Optional<ApplicationCommandInteractionOption> option) {
    return option
        .flatMap(ApplicationCommandInteractionOption::getValue)
        .map(ApplicationCommandInteractionOptionValue::asString);
  }
  
  @SuppressWarnings("OptionalGetWithoutIsPresent")
  public static String asRequiredString(Optional<ApplicationCommandInteractionOption> option) {
    return asString(option).get();
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  public static Optional<Long> asLong(Optional<ApplicationCommandInteractionOption> option) {
    return option
        .flatMap(ApplicationCommandInteractionOption::getValue)
        .map(ApplicationCommandInteractionOptionValue::asLong);
  }

}
