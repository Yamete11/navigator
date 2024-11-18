package org.example.service.observer;

import org.example.model.Route;

public interface RouteObservable {
    void addObserver(RouteObserver observer);
    void removeObserver(RouteObserver observer);
    void notifyObservers(RouteEventType eventType, Route route);
}
