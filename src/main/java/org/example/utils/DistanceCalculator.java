package org.example.utils;

import org.example.model.City;
import org.example.model.CityConnection;
import org.example.model.RouteCity;

import java.util.List;
import java.util.Optional;

public class DistanceCalculator {
    public static Double calculateDistance(Optional<City> city1, Optional<City> city2) {
        City cityA = city1.get();
        City cityB = city2.get();
        return Math.sqrt(Math.pow(cityB.getX() - cityA.getX(), 2) + Math.pow(cityB.getY() - cityA.getY(), 2));
    }

    public static Double calculateDistance(City city1, City city2) {
        return Math.sqrt(Math.pow(city2.getX() - city1.getX(), 2) + Math.pow(city2.getY() - city1.getY(), 2));
    }

    public static Double calculateDistance(CityConnection cityConnection) {
        City cityA = cityConnection.getFirstCity();
        City cityB = cityConnection.getSecondCity();
        return Math.sqrt(Math.pow(cityB.getX() - cityA.getX(), 2) + Math.pow(cityB.getY() - cityA.getY(), 2));
    }

    public static Double calculateTotalDistance(List<RouteCity> cities) {
        double result = 0.0d;
        for (int i = 0; i < cities.size() - 1; i++) {
            result += calculateDistance(cities.get(i).getCity(), cities.get(i + 1).getCity());
        }
        return result;
    }
}
