package net.jfsanchez.hexdemo.domain.port.output;

import jakarta.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.valueobject.Page;
import net.jfsanchez.hexdemo.domain.valueobject.Pagination;

public interface UserRepositoryPort {
    Optional<User> findById(UUID id);

    @Nonnull
    User save(User user);

    Page<User> list(Pagination pagination);
}
