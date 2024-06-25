package net.jfsanchez.hexdemo.domain.port.entity;

import java.util.UUID;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.valueobject.MaritalStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void name_must_not_be_empty() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new User(UUID.randomUUID(), "", 20, MaritalStatus.SINGLE)
        );
    }

    @Test
    void age_must_be_positive() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new User(UUID.randomUUID(), UUID.randomUUID().toString(), -1, MaritalStatus.SINGLE)
        );
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new User(UUID.randomUUID(), UUID.randomUUID().toString(), 0, MaritalStatus.SINGLE)
        );
    }

    @Test
    @DisplayName("Minors must be single")
    void minors_must_be_single() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new User(UUID.randomUUID(), UUID.randomUUID().toString(), User.MIN_ADULT_AGE - 1, MaritalStatus.MARRIED)
        );
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new User(UUID.randomUUID(), UUID.randomUUID().toString(), User.MIN_ADULT_AGE - 1, MaritalStatus.DIVORCED)
        );
    }
}