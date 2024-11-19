package org.example.service;

import org.example.model.CityConnection;
import org.example.model.Route;

import java.util.List;

public interface RouteService extends GenericService<Route> {
    List<Route> getRoutesByCityId(Long cityId);

    Route checkIfRouteExists(Long city1Id, Long city2Id);
    List<CityConnection> getCityConnectionsByRouteId(Long routeId);

}
