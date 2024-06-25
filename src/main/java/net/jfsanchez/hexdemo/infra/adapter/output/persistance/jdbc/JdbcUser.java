package net.jfsanchez.hexdemo.infra.adapter.output.persistance.jdbc;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.valueobject.MaritalStatus;

@Builder
@Serdeable
@MappedEntity("user")
record JdbcUser(
    @Id UUID id,
    @Nonnull String name,
    @Nonnull Integer age,
    @Nonnull MaritalStatus maritalStatus,
    @Nonnull Instant createdAt
) {
    public static JdbcUser from(User user) {
        return JdbcUser.builder()
            .id(user.uuid())
            .name(user.name())
            .age(user.age())
            .maritalStatus(user.maritalStatus())
            .createdAt(Instant.now())
            .build();
    }

    public User toDomain() {
        return new User(id, name, age, maritalStatus);
    }
}
