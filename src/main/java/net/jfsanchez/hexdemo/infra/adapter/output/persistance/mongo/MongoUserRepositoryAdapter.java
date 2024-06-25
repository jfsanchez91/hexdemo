package net.jfsanchez.hexdemo.infra.adapter.output.persistance.mongo;

import io.micronaut.context.annotation.Requires;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import jakarta.annotation.Nonnull;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.port.output.UserRepositoryPort;
import net.jfsanchez.hexdemo.domain.valueobject.Page;
import net.jfsanchez.hexdemo.domain.valueobject.Pagination;

@Singleton
@Requires(env = "mongo")
@RequiredArgsConstructor
final class MongoUserRepositoryAdapter implements UserRepositoryPort {
    private final MongoUserRepository mongoUserRepository;

    @Override
    public Optional<User> findById(UUID id) {
        return mongoUserRepository.findById(id).map(MongoUser::toDomain);
    }

    @Nonnull
    @Override
    public User save(User user) {
        return mongoUserRepository.save(MongoUser.from(user)).toDomain();
    }

    @Override
    public Page<User> list(Pagination pagination) {
        final var page = mongoUserRepository.findAll(Pageable.from(
            pagination.pageNumber(),
            pagination.pageSize(),
            Sort.of(Sort.Order.asc("createdAt"))
        ));
        return Page.of(map(page.getContent()), page.getTotalSize(), pagination);
    }

    private static List<User> map(List<MongoUser> mongoUser) {
        return mongoUser.stream().map(MongoUser::toDomain).toList();
    }
}
