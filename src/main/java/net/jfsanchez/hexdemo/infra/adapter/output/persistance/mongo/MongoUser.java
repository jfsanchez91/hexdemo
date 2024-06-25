package net.jfsanchez.hexdemo.infra.adapter.output.persistance.mongo;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.annotation.Nonnull;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.valueobject.MaritalStatus;

@Builder
@MappedEntity
record MongoUser(
    @Id UUID id,
    @Nonnull String name,
    @Nonnull Integer age,
    @Nonnull MaritalStatus maritalStatus,
    @Nonnull Instant createdAt
) {
    public static MongoUser from(User user) {
        return MongoUser.builder()
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
