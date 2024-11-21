package org.example.utils;

import lombok.Getter;
import org.example.dao.implementation.CityConnectionMapperImpl;
import org.example.dao.implementation.CityMapperImpl;
import org.example.model.City;
import org.example.model.CityConnection;
import org.example.service.CityConnectionService;
import org.example.service.CityService;
import org.example.service.implementation.CityConnectionServiceImpl;
import org.example.service.implementation.CityServiceImpl;
import org.example.service.implementation.RouteCityServiceImpl;

import java.util.List;
import java.util.Objects;

public class GraphLoader {
    @Getter
    private static final Graph graph = new Graph();
    private static CityService cityService;
    private static CityConnectionService cityConnectionService;

    static {
        cityService = new CityServiceImpl(new CityMapperImpl(), new CityConnectionServiceImpl(new CityConnectionMapperImpl()),new RouteCityServiceImpl());
        cityConnectionService = new CityConnectionServiceImpl(new CityConnectionMapperImpl());
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

    public static Graph getGraph() {
        loadGraph(cityService, cityConnectionService);
        return graph;
    }
}

