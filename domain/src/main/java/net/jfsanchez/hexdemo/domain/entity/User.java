package net.jfsanchez.hexdemo.domain.entity;

import jakarta.annotation.Nonnull;
import java.util.UUID;
import net.jfsanchez.hexdemo.domain.valueobject.MaritalStatus;

public record User(
    @Nonnull UUID uuid,
    @Nonnull String name,
    @Nonnull Integer age,
    @Nonnull MaritalStatus maritalStatus
) {
    public static final int MIN_VALID_AGE = 1;
    public static final int MAX_VALID_AGE = 120;
    public static final int MIN_ADULT_AGE = 18;

    public User {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (age < MIN_VALID_AGE || age > MAX_VALID_AGE) {
            throw new IllegalArgumentException("Age must be greater than %s and less than %s".formatted(MIN_VALID_AGE, MAX_VALID_AGE));
        }
        if (age < MIN_ADULT_AGE && MaritalStatus.SINGLE != maritalStatus) {
            throw new IllegalArgumentException("Minors marital status must be SINGLE");
        }
    }
}
