package net.jfsanchez.hexdemo.domain.valueobject;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public record Pagination(
    @Nonnull Integer pageNumber,
    @Nonnull Integer pageSize
) {
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;

    public Pagination() {
        this(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }

    public static Pagination of(@Nullable Integer pageNumber, @Nullable Integer pageSize) {
        if (pageNumber == null) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_NUMBER;
        }
        return new Pagination(pageNumber, pageSize);
    }
}
