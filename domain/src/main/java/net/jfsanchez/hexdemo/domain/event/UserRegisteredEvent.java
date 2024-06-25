package net.jfsanchez.hexdemo.domain.event;

import jakarta.annotation.Nonnull;
import java.util.UUID;

public record UserRegisteredEvent(
    @Nonnull UUID uuid,
    @Nonnull String name
) implements Event {
}
