package net.jfsanchez.hexdemo.infra.adapter.output.event;

import io.micronaut.context.event.ApplicationEventPublisher;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jfsanchez.hexdemo.domain.event.Event;
import net.jfsanchez.hexdemo.domain.port.output.EventPublisher;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class EventPublisherAdapter implements EventPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public <T extends Event> void publish(T event) {
        log.info("Publishing event {}.", event);
        publisher.publishEvent(event);
    }
}
