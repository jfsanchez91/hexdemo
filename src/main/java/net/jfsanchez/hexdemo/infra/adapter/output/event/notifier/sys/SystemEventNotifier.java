package net.jfsanchez.hexdemo.infra.adapter.output.event.notifier.sys;

public interface SystemEventNotifier {
    void notify(String appName, String message);
}
