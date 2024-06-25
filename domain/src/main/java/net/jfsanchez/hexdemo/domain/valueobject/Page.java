package net.jfsanchez.hexdemo.domain.valueobject;

import jakarta.annotation.Nonnull;
import java.util.List;

public record Page<T>(
    @Nonnull List<T> items,
    @Nonnull Long totalSize,
    @Nonnull Pagination pagination
) {
    public static <T> Page<T> of(List<T> items, Long totalSize, Pagination pagination) {
        return new Page<>(items, totalSize, pagination);
    }
}
