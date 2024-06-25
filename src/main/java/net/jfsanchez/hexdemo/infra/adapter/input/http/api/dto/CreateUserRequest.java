package net.jfsanchez.hexdemo.infra.adapter.input.http.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import net.jfsanchez.hexdemo.domain.valueobject.MaritalStatus;

@Builder
@Serdeable.Deserializable
public record CreateUserRequest(
    @Nonnull String name,
    @Nonnull Integer age,
    @Nonnull @JsonProperty("marital_status") MaritalStatus maritalStatus
) {
}
