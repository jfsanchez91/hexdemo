package net.jfsanchez.hexdemo.domain.port.input;

import jakarta.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.valueobject.MaritalStatus;
import net.jfsanchez.hexdemo.domain.valueobject.Page;
import net.jfsanchez.hexdemo.domain.valueobject.Pagination;

public interface UserUseCase {
    @Nonnull
    User register(@Nonnull RegisterUserCommand command);

    Optional<User> findById(@Nonnull FindUserByIdQuery query);

    Page<User> list(@Nonnull ListUserQuery query);

    record RegisterUserCommand(
        @Nonnull UUID id,
        @Nonnull String name,
        @Nonnull Integer age,
        @Nonnull MaritalStatus maritalStatus
    ) {
        public static RegisterUserCommand of(String name, Integer age, MaritalStatus maritalStatus) {
            return RegisterUserCommand.of(UUID.randomUUID(), name, age, maritalStatus);
        }

        public static RegisterUserCommand of(UUID id, String name, Integer age, MaritalStatus maritalStatus) {
            return new RegisterUserCommand(id, name, age, maritalStatus);
        }
    }

    record FindUserByIdQuery(
        @Nonnull UUID uuid
    ) {
    }

    record ListUserQuery(
        @Nonnull Pagination pagination
    ) {
    }
}
