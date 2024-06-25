package net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import java.util.UUID;
import net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.kafka.dto.UserRegisteredMessage;

@KafkaClient
public interface KafkaEventNotifier {
    String USER_REGISTERED_TOPIC = "user_registered";

    @Topic(USER_REGISTERED_TOPIC)
    void notifyUserRegisteredEvent(@KafkaKey UUID id, UserRegisteredMessage message);
}
