package net.jfsanchez.hexdemo.infra.adapter.input.kafka.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;
import lombok.Builder;

@Builder
@Serdeable.Deserializable
public record RegisterMarriedUserMessage(
    @Nonnull String name,
    @Nonnull Integer age
) {
}
