package net.jfsanchez.hexdemo.infra.adapter.input.http.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;
import java.util.UUID;
import lombok.Builder;

@Builder
@Serdeable.Serializable
public record CreateUserResponse(
    @Nonnull @JsonProperty("id") UUID id
) {
}
