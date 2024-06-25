package net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.sys;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jfsanchez.hexdemo.domain.event.UserRegisteredEvent;

@Slf4j
@Singleton
@RequiredArgsConstructor
class SystemEventNotifierAdapter {
    private final SystemEventNotifier systemEventNotifier;

    @Async
    @EventListener
    public void onUserRegisteredEvent(UserRegisteredEvent event) {
        log.info("Notifying user registered notification.");
        systemEventNotifier.notify("hexdemo-server", "New user with name '%s' registered.".formatted(event.name()));
    }
}
