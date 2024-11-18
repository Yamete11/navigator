package org.example.service.observer;

import org.example.model.Route;

public interface RouteObserver {
    void update(RouteEventType eventType, Route route);
}
