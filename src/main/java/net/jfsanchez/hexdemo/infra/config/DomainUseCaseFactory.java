package net.jfsanchez.hexdemo.infra.config;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import net.jfsanchez.hexdemo.domain.port.input.UserUseCase;
import net.jfsanchez.hexdemo.domain.port.input.impl.UserUseCaseImpl;
import net.jfsanchez.hexdemo.domain.port.output.EventPublisher;
import net.jfsanchez.hexdemo.domain.port.output.UserRepositoryPort;

@Factory
final class DomainUseCaseFactory {
    @Bean
    UserUseCase userUseCase(UserRepositoryPort repository, EventPublisher eventPublisher) {
        return new UserUseCaseImpl(repository, eventPublisher);
    }
}
