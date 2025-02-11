package com.mattmerr.beets.commands;

import static java.lang.String.format;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mattmerr.beets.vc.VCManager;
import com.mattmerr.beets.vc.VCSession;
import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.interaction.ButtonInteractEvent;
import discord4j.core.event.domain.interaction.InteractionCreateEvent;
import discord4j.core.event.domain.interaction.SlashCommandEvent;
import discord4j.core.spec.InteractionApplicationCommandCallbackSpec;
import java.awt.Button;
import reactor.core.publisher.Mono;

@CommandDesc(
    name = "punt",
    description = "Punt a beet",
    options = {})
@Singleton
public class PuntCommand extends CommandBase implements ButtonCommand {

  private final VCManager vcManager;

  @Inject
  PuntCommand(VCManager vcManager) {
    this.vcManager = vcManager;
  }

  public Mono<Void> execute(InteractionCreateEvent event) {
    logCall(event);

    VCSession session = vcManager.pollSession(event);
    if (session.getTrackScheduler().punt()) {
      return event.reply(
          format("<@%s> Punted!", event.getInteraction().getUser().getId().asString()));
    } else {
      return event.reply(
          format("<@%s> Cannot punt right now!", event.getInteraction().getUser().getId().asString()));
    }
  }

  @Override
  public Mono<Void> execute(ButtonInteractEvent event) {
    return execute((InteractionCreateEvent) event);
  }

  @Override
  public Mono<Void> execute(SlashCommandEvent event) {
    return execute((InteractionCreateEvent) event);
  }
}
