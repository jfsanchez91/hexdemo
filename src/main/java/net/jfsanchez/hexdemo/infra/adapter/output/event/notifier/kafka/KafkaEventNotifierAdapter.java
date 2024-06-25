package net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.kafka;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jfsanchez.hexdemo.domain.event.UserRegisteredEvent;
import net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.kafka.dto.UserRegisteredMessage;

@Slf4j
@Singleton
@RequiredArgsConstructor
class KafkaEventNotifierAdapter {
    private final KafkaEventNotifier kafkaEventNotifier;

    @Async
    @EventListener
    public void onUserRegisteredEvent(UserRegisteredEvent event) {
        log.info("Sending user registered message to Kafka.");
        kafkaEventNotifier.notifyUserRegisteredEvent(event.uuid(), UserRegisteredMessage.of(event));
    }
}
