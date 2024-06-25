package net.jfsanchez.hexdemo.infra.adapter.input.http.error.handler;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import net.jfsanchez.hexdemo.infra.adapter.input.http.error.dto.HttpErrorObject;

@Singleton
class IllegalArgumentExceptionHandler implements ExceptionHandler<IllegalArgumentException, HttpResponse<HttpErrorObject>> {
    @Override
    public HttpResponse<HttpErrorObject> handle(HttpRequest request, IllegalArgumentException exception) {
        return HttpResponse.badRequest(HttpErrorObject.from(exception));
    }
}
