package net.jfsanchez.hexdemo.infra.adapter.input.http.error.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;

@Serdeable.Serializable
public record HttpErrorObject(
    @Nonnull String error
) {
    public static HttpErrorObject from(Throwable throwable) {
        return new HttpErrorObject(throwable.getMessage());
    }

    public static HttpErrorObject of(String message) {
        return new HttpErrorObject(message);
    }
}
