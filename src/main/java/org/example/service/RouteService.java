package org.example.service;

import org.example.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService extends GenericService<Route> {
    List<Route> getRoutesByCityId(Long cityId);
}
