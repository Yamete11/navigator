package org.example.utils;

import org.example.model.City;
import org.example.model.RouteCity;

import java.util.List;

public interface Navigator {
    List<RouteCity> findRoute(City start, City end);

    List<RouteCity> findRoute(String firstCity, String secondCity);

    void setStrategy(RouteFindingStrategy strategy);
}
