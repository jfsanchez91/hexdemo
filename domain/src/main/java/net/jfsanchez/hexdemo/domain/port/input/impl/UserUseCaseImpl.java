package net.jfsanchez.hexdemo.domain.port.input.impl;

import jakarta.annotation.Nonnull;
import java.util.Optional;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.event.UserRegisteredEvent;
import net.jfsanchez.hexdemo.domain.port.input.UserUseCase;
import net.jfsanchez.hexdemo.domain.port.output.EventPublisher;
import net.jfsanchez.hexdemo.domain.port.output.UserRepositoryPort;
import net.jfsanchez.hexdemo.domain.valueobject.Page;

public final class UserUseCaseImpl implements UserUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final EventPublisher eventPublisher;

    public UserUseCaseImpl(UserRepositoryPort userRepositoryPort, EventPublisher eventPublisher) {
        this.userRepositoryPort = userRepositoryPort;
        this.eventPublisher = eventPublisher;
    }

    @Nonnull
    @Override
    public User register(@Nonnull RegisterUserCommand command) {
        final var user = new User(
            command.id(),
            command.name(),
            command.age(),
            command.maritalStatus()
        );
        userRepositoryPort.save(user);
        eventPublisher.publish(new UserRegisteredEvent(user.uuid(), user.name()));
        return user;
    }

    @Override
    public Optional<User> findById(@Nonnull FindUserByIdQuery query) {
        return userRepositoryPort.findById(query.uuid());
    }

    @Override
    public Page<User> list(@Nonnull ListUserQuery query) {
        return userRepositoryPort.list(query.pagination());
    }
}
