package net.jfsanchez.hexdemo.infra.adapter.input.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Singleton;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.jfsanchez.hexdemo.domain.port.input.UserUseCase;
import net.jfsanchez.hexdemo.domain.valueobject.MaritalStatus;
import net.jfsanchez.hexdemo.infra.adapter.input.kafka.dto.RegisterMarriedUserMessage;

@Singleton
@RequiredArgsConstructor
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class RegisterUserKafkaListener {
    public static final String REGISTER_MARRIED_USER_TOPIC = "register_married_user";

    private final UserUseCase userUseCase;

    @Topic(REGISTER_MARRIED_USER_TOPIC)
    public void registerMarriedUser(@KafkaKey UUID id, RegisterMarriedUserMessage message) {
        final var command = UserUseCase.RegisterUserCommand.of(
            id,
            message.name(),
            message.age(),
            MaritalStatus.MARRIED
        );
        userUseCase.register(command);
    }
}
