package org.example.utils;

import org.example.model.City;
import org.example.model.CityConnection;

import java.util.Optional;

public class DistanceCalculator {
    public static Double calculateDistance(Optional<City> city1, Optional<City> city2) {
        City cityA = city1.get();
        City cityB = city2.get();
        return Math.sqrt(Math.pow(cityB.getX() - cityA.getX(), 2) + Math.pow(cityB.getY() - cityA.getY(), 2));
    }

    public static Double calculateDistance(CityConnection cityConnection) {
        City cityA = cityConnection.getFirstCity();
        City cityB = cityConnection.getSecondCity();
        return Math.sqrt(Math.pow(cityB.getX() - cityA.getX(), 2) + Math.pow(cityB.getY() - cityA.getY(), 2));
    }
}
