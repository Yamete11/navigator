package org.example.utils;

import org.example.model.City;
import org.example.model.CityConnection;
import org.example.model.Route;

import java.util.List;

public interface Navigator {
    Route findRoute(City start, City end);

    Route findRoute(String firstCity, String secondCity);

    Double calculateDistance(CityConnection cityConnection);

    List<CityConnection> findAllCityConnections();
}
