package net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.sys.impl;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import lombok.extern.slf4j.Slf4j;
import net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.sys.SystemEventNotifier;

@Slf4j
@Factory
final class SystemEventNotifierFactory {
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    @Bean
    SystemEventNotifier systemEventNotifier() {
        if (OS_NAME.startsWith("windows")) {
            return new WindowsSystemEventNotifier();
        } else if (OS_NAME.startsWith("linux")) {
            return new LinuxSystemEventNotifier();
        } else if (OS_NAME.startsWith("mac")) {
            return new MacosSystemEventNotifier();
        }
        throw new IllegalStateException("Unexpected value: " + OS_NAME);
    }
}
