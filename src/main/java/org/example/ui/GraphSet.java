package org.example.ui;

import org.example.model.City;
import org.example.model.CityConnection;
import org.example.service.CityConnectionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class GraphSet {

    private final Set<CityConnection> uniqueCityConnections;
    private final Set<City> uniqueCities;

    public GraphSet(CityConnectionService cityConnectionService) {
        this.uniqueCityConnections = uniqueCityConnection(cityConnectionService.findAll());
        this.uniqueCities = uniqueCities(uniqueCityConnections);
    }

    public GraphSet(List<CityConnection> connections) {
        this.uniqueCityConnections = uniqueCityConnection(connections);
        this.uniqueCities = uniqueCities(uniqueCityConnections);
    }

    public Set<CityConnection> uniqueCityConnection(List<CityConnection> connections) {
        Set<CityConnection> uniqueCityConnections =
                new TreeSet<>((c1, c2) -> {
                    if (c1.getId().equals(c2.getId())) {
                        return 0;
                    }
                    return c1.getId().compareTo(c2.getId());
                });
        uniqueCityConnections.addAll(connections);
        return uniqueCityConnections;
    }

    public Set<CityConnection> getUniqueCityConnections() {
        return new HashSet<CityConnection>(uniqueCityConnections);
    }

    private Set<City> uniqueCities(Set<CityConnection> uniqueCityConnections) {
        Set<City> uniqueCities = new TreeSet<>((c1, c2) -> {
            if (c1.getId().equals(c2.getId())) {
                return 0;
            }
            return c1.getId().compareTo(c2.getId());
        });
        for (CityConnection connection : uniqueCityConnections) {
            uniqueCities.add(connection.getFirstCity());
            uniqueCities.add(connection.getSecondCity());
        }
        return uniqueCities;
    }

    public Set<City> getUniqueCity() {
        return new HashSet<City>(uniqueCities);
    }

}
