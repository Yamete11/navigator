package org.example.ui;

import org.example.model.CityConnection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FunctionalInterface
public interface ICityConnectionChecker {

    Set<CityConnection> uniqueCityConnection(List<CityConnection> connections);

    default boolean isCityConnectionIdDuplicate(CityConnection c1, CityConnection c2) {
        Set<Long> uniqueId = new HashSet<>();
        uniqueId.add(c1.getFirstCity().getId());
        uniqueId.add(c2.getFirstCity().getId());
        uniqueId.add(c1.getSecondCity().getId());
        uniqueId.add(c2.getSecondCity().getId());
        return uniqueId.size()==2;
    }

    default boolean compareConnectionId(CityConnection c1, CityConnection c2) {
        boolean containsFirstCity =
                c2.getFirstCity().getId().equals(c1.getFirstCity().getId()) ||
                        c2.getFirstCity().getId().equals(c1.getSecondCity().getId());

        boolean containsSecondCity =
                c2.getSecondCity().getId().equals(c1.getFirstCity().getId()) ||
                        c2.getSecondCity().getId().equals(c1.getSecondCity().getId());

        return (containsFirstCity && containsSecondCity);
    }

    default boolean compareConnectionXY(CityConnection c1, CityConnection c2) {
        boolean containsFirstCityX =
                c2.getFirstCity().getX().equals(c1.getFirstCity().getX()) ||
                        c2.getFirstCity().getX().equals(c1.getSecondCity().getX());

        boolean containsFirstCityY =
                c2.getFirstCity().getY().equals(c1.getFirstCity().getY()) ||
                        c2.getFirstCity().getY().equals(c1.getSecondCity().getY());

        boolean containsSecondCityX =
                c2.getSecondCity().getX().equals(c1.getFirstCity().getX()) ||
                        c2.getSecondCity().getX().equals(c1.getSecondCity().getX());

        boolean containsSecondCityY =
                c2.getSecondCity().getY().equals(c1.getFirstCity().getY()) ||
                        c2.getSecondCity().getY().equals(c1.getSecondCity().getY());

        return ((containsFirstCityX && containsFirstCityY) && (containsSecondCityX && containsSecondCityY));
    }
}

