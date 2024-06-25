package net.jfsanchez.hexdemo.infra.adapter.output.persistance.memory;

import io.micronaut.context.annotation.Requires;
import jakarta.annotation.Nonnull;
import jakarta.inject.Singleton;
import java.time.Instant;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.port.output.UserRepositoryPort;
import net.jfsanchez.hexdemo.domain.valueobject.Page;
import net.jfsanchez.hexdemo.domain.valueobject.Pagination;

@Singleton
@Requires(env = "in-memory")
@RequiredArgsConstructor
final class InMemoryUserRepositoryAdapter implements UserRepositoryPort {
    private static final ConcurrentHashMap<UUID, MemoryUser> users = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(users.get(id)).map(MemoryUser::user);
    }

    @Nonnull
    @Override
    public User save(User user) {
        users.put(user.uuid(), MemoryUser.of(user));
        return user;
    }

    @Override
    public Page<User> list(Pagination pagination) {
        final var skip = pagination.pageNumber() * pagination.pageSize();
        final var paginatedUsers = users.values().stream()
            .skip(skip)
            .limit(pagination.pageSize())
            .sorted(Comparator.comparing(user -> user.createdAt))
            .map(MemoryUser::user)
            .toList();

        return Page.of(paginatedUsers, (long) users.size(), pagination);
    }

    record MemoryUser(
        @Nonnull User user,
        @Nonnull Instant createdAt
    ) {
        public static MemoryUser of(User user) {
            return new MemoryUser(user, Instant.now());
        }
    }
}
