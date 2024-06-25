package net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.sys.impl;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.sys.SystemEventNotifier;

@Slf4j
final class LinuxSystemEventNotifier implements SystemEventNotifier {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    @Override
    public void notify(String appName, String message) {
        EXECUTOR_SERVICE.submit(() -> {
            try {
                new ProcessBuilder("/usr/bin/notify-send", "--app-name", appName, message).start().waitFor();
            } catch (InterruptedException | IOException e) {
                log.warn("There was an error sending the notification", e);
            }
        });
    }
}
