package org.example.utils.implementation;

import lombok.Setter;
import org.example.model.City;
import org.example.model.CityConnection;
import org.example.model.RouteCity;
import org.example.service.CityConnectionService;
import org.example.service.CityService;
import org.example.service.implementation.CityConnectionServiceImpl;
import org.example.service.implementation.CityServiceImpl;
import org.example.utils.Navigator;
import org.example.utils.RouteFindingStrategy;

import java.util.List;
import java.util.Optional;

public class CityNavigator implements Navigator {
    private final CityService cityService;
    private final CityConnectionService cityConnectionService;
    @Setter
    private RouteFindingStrategy strategy;

    public CityNavigator() {
        this.cityService = new CityServiceImpl();
        this.cityConnectionService = new CityConnectionServiceImpl();
    }

    @Override
    public List<RouteCity> findRoute(City start, City end) {
        return strategy.findRoute(start, end);
    }

    @Override
    public List<RouteCity> findRoute(String firstCity, String secondCity) {
        City start = cityService.getCityByTitle(firstCity).get();
        City end = cityService.getCityByTitle(secondCity).get();
        return strategy.findRoute(start, end);
    }

    @Override
    public Double calculateDistance(CityConnection cityConnection) {
        if (cityConnection.getDistance() != 0.0d) return cityConnection.getDistance();

        City city1 = cityConnection.getFirstCity();
        City city2 = cityConnection.getSecondCity();
        return Math.sqrt(Math.pow(city2.getX() - city1.getX(), 2) + Math.pow(city2.getY() - city1.getY(), 2));
    }

    @Override
    public double calculateDistance(Optional<City> city1, Optional<City> city2) {
        return Math.sqrt(Math.pow(city2.get().getX() - city1.get().getX(), 2) + Math.pow(city2.get().getY() - city1.get().getY(), 2));
    }

    @Override
    public List<CityConnection> findAllCityConnections() {
        return cityConnectionService.findAll();
    }
}
