package org.example.service;

import org.example.model.CityConnection;
import org.example.model.Route;
import org.example.service.observer.RouteObservable;

import java.util.List;

public interface RouteService extends GenericService<Route>, RouteObservable {
    List<Route> getRoutesByCityId(Long cityId);

    Route checkIfRouteExists(Long city1Id, Long city2Id);
    List<CityConnection> getCityConnectionsByRouteId(Long routeId);
    void refresher();

}
