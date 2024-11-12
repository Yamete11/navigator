package org.example.utils;

import org.example.model.City;
import org.example.model.CityConnection;
import org.example.model.RouteCity;

import java.util.List;
import java.util.Optional;

public interface Navigator {
    List<RouteCity> findRoute(City start, City end);

    List<RouteCity> findRoute(String firstCity, String secondCity);

    Double calculateDistance(CityConnection cityConnection);

    List<CityConnection> findAllCityConnections();

    double calculateDistance(Optional<City> byId, Optional<City> byId1);

    void setStrategy(RouteFindingStrategy strategy);
}
