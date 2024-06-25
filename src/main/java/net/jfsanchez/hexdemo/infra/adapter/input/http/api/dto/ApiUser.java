package net.jfsanchez.hexdemo.infra.adapter.input.http.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;
import java.util.UUID;
import lombok.Builder;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.valueobject.MaritalStatus;

@Builder
@Serdeable.Serializable
public record ApiUser(
    @Nonnull UUID id,
    @Nonnull String name,
    @Nonnull Integer age,
    @Nonnull @JsonProperty("marital_status") MaritalStatus maritalStatus
) {
    public static ApiUser of(User user) {
        return ApiUser.builder()
            .id(user.uuid())
            .name(user.name())
            .age(user.age())
            .maritalStatus(user.maritalStatus())
            .build();
    }
}
