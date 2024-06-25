package net.jfsanchez.hexdemo.domain.port.output;

import net.jfsanchez.hexdemo.domain.event.Event;

public interface EventPublisher {
    <T extends Event> void publish(T event);
}
