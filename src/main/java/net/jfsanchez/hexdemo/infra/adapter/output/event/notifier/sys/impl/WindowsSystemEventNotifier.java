package net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.sys.impl;

import lombok.extern.slf4j.Slf4j;
import net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.sys.SystemEventNotifier;

@Slf4j
final class WindowsSystemEventNotifier implements SystemEventNotifier {
    @Override
    public void notify(String appName, String message) {
        log.warn("Windows event notifier not implemented yet");
    }
}
