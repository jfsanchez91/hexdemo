package net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.kafka.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;
import java.util.UUID;
import lombok.Builder;
import net.jfsanchez.hexdemo.domain.event.UserRegisteredEvent;

@Builder
@Serdeable.Serializable
public record UserRegisteredMessage(
    @Nonnull UUID uuid,
    @Nonnull String name
) {
    public static UserRegisteredMessage of(UserRegisteredEvent event) {
        return UserRegisteredMessage.builder()
            .uuid(event.uuid())
            .name(event.name())
            .build();
    }
}
