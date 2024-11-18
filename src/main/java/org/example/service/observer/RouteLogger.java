package org.example.service.observer;

import org.example.model.Route;

public class RouteLogger implements RouteObserver {

    @Override
    public void update(RouteEventType eventType, Route route) {
        switch (eventType) {
            case ROUTE_ADDED -> System.out.println("Route added: " + route);
            case ROUTE_UPDATED -> System.out.println("Route updated: " + route);
            case ROUTE_DELETED -> System.out.println("Route deleted: " + route);
        }
    }
}
