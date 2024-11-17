package org.example.ui;

import org.example.model.City;
import org.example.model.CityConnection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GraphSet implements ICityConnectionChecker{

    private final Set<CityConnection> uniqueCityConnections;

    public GraphSet(INavigator navigator) {
        this.uniqueCityConnections = uniqueCityConnection(navigator.findAllCityConnections());
    }

    public Set<CityConnection> uniqueCityConnection(List<CityConnection> connections) {
        Set<CityConnection> uniqueCityConnections =
                new TreeSet<>((c1, c2) -> isCityConnectionIdDuplicate(c1,c2)?0:1);
        uniqueCityConnections.addAll(connections);
        return uniqueCityConnections;
    }

    public Set<CityConnection> getUniqueCityConnections() {
        return new HashSet<CityConnection>(uniqueCityConnections);
    }
}
