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
}

