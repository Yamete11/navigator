package org.example.utils;

import lombok.Getter;
import org.example.model.City;
import org.example.model.CityConnection;
import org.example.service.CityConnectionService;
import org.example.service.CityService;
import org.example.service.implementation.CityConnectionServiceImpl;
import org.example.service.implementation.CityServiceImpl;

import java.util.List;
import java.util.Objects;

public class GraphLoader {
    @Getter
    private static final Graph graph = new Graph();

    static {
        CityService cityService = new CityServiceImpl();
        CityConnectionService cityConnectionService = new CityConnectionServiceImpl();
        loadGraph(cityService, cityConnectionService);
    }

    private static void loadGraph(CityService cityService, CityConnectionService cityConnectionService) {
        for (City city : cityService.findAll()) {
            graph.addNode(new Node(city.getId()));
            List<CityConnection> neighbors = cityConnectionService.getCityConnectionsByCityId(city.getId());
            for (CityConnection neighbor : neighbors) {
                Long neighborId = Objects.equals(neighbor.getFirstCity().getId(), city.getId()) ? neighbor.getSecondCity().getId() : neighbor.getFirstCity().getId();

                graph.addEdge(city.getId(), neighborId, neighbor.getDistance());

            }
        }
    }
}

