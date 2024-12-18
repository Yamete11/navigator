package org.example.service;

import org.example.model.City;
import org.example.model.RouteCity;

import java.util.List;

public interface RouteCityService extends GenericService<RouteCity> {
    List<City> getCitiesByRouteId(Long id);
    void deleteByCityId(Long id);

    void deleteByRouteId(Long id);

    void createBatch(List<RouteCity> routeCities);

}
