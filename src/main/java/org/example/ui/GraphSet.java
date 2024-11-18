package org.example.ui;

import org.example.model.CityConnection;
import org.example.service.CityConnectionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GraphSet {

    private final Set<CityConnection> uniqueCityConnections;

    public GraphSet(CityConnectionService cityConnectionService) {
        this.uniqueCityConnections = uniqueCityConnection(cityConnectionService.findAll());
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
}
