package net.jfsanchez.hexdemo.infra.adapter.output.persistance.jdbc;

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
@RequiredArgsConstructor
@Requires(missingBeans = UserRepositoryPort.class)
final class JdbcUserRepositoryAdapter implements UserRepositoryPort {
    private final JdbcUserRepository jdbcRepository;

    private static List<User> map(List<JdbcUser> jdbcUsers) {
        return jdbcUsers.stream().map(JdbcUser::toDomain).toList();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jdbcRepository.findById(id).map(JdbcUser::toDomain);
    }

    @Nonnull
    @Override
    public User save(User user) {
        return jdbcRepository.save(JdbcUser.from(user)).toDomain();
    }

    @Override
    public Page<User> list(Pagination pagination) {
        final var page = jdbcRepository.findAll(Pageable.from(
            pagination.pageNumber(),
            pagination.pageSize(),
            Sort.of(Sort.Order.asc("createdAt"))
        ));
        return Page.of(map(page.getContent()), page.getTotalSize(), pagination);
    }
}
