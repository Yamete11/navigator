package org.example.utils.implementation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.*;
import org.example.service.*;
import org.example.service.implementation.*;
import org.example.utils.Navigator;

import java.util.*;

public class CityNavigator implements Navigator {
    private final CityService cityService;
    private final CityConnectionService cityConnectionService;

    public CityNavigator() {
        this.cityService = new CityServiceImpl();
        this.cityConnectionService = new CityConnectionServiceImpl();
    }

    @Override
    public List<RouteCity> findRoute(City start, City end) {
        return getRoute(start, end);
    }

    @Override
    public List<RouteCity> findRoute(String firstCity, String secondCity) {
        City start = cityService.getCityByTitle(firstCity).get();
        City end = cityService.getCityByTitle(secondCity).get();
        return getRoute(start, end);
    }

    private List<RouteCity> getRoute(City start, City end) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(Node::getDistance));
        Map<Long, Double> distances = new HashMap<>();
        Map<Long, Long> previousCities = new HashMap<>();
        Set<Long> visited = new HashSet<>();

        for (City city : cityService.findAll()) {
            distances.put(city.getId(), Double.POSITIVE_INFINITY);
        }
        distances.put(start.getId(), 0.0d);

        queue.add(new Node(start.getId(), 0.0d));
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Long currentCityId = currentNode.getId();

            if (Objects.equals(currentCityId, end.getId())) {
                return buildRoute(end, previousCities);
            }

            if (visited.contains(currentCityId)) continue;
            visited.add(currentCityId);

            List<CityConnection> neighbors = cityConnectionService.getCityConnectionsByCityId(currentCityId);
            for (CityConnection connection : neighbors) {
                Long neighborCityId = Objects.equals(connection.getFirstCity().getId(), currentCityId) ?
                        connection.getSecondCity().getId() : connection.getFirstCity().getId();
                double newDistance = distances.get(currentCityId) + calculateDistance(connection);

                if (newDistance < distances.get(neighborCityId)) {
                    distances.put(neighborCityId, newDistance);
                    previousCities.put(neighborCityId, currentCityId);
                    queue.add(new Node(neighborCityId, newDistance));
                }
            }
        }
        return null;
    }

    private List<RouteCity> buildRoute(City end, Map<Long, Long> previousCities) {
        List<RouteCity> routeCities = new ArrayList<>();

        for (Long at = end.getId(), counter = 0L; at != null;
             at = previousCities.get(at), counter++) {
            City currentCity = cityService.findAll().get((int) (at - 1));
            RouteCity routeCity = new RouteCity(currentCity, null, counter);
            routeCities.add(routeCity);
        }

        return routeCities;
    }

    @Override
    public Double calculateDistance(CityConnection cityConnection) {
        if (cityConnection.getDistance() != 0.0d) return cityConnection.getDistance();

        City city1 = cityConnection.getFirstCity();
        City city2 = cityConnection.getSecondCity();
        return Math.sqrt(Math.pow(city2.getX() - city1.getX(), 2) + Math.pow(city2.getY() - city1.getY(), 2));
    }

    @Override
    public List<CityConnection> findAllCityConnections() {
        return cityConnectionService.findAll();
    }

    @Setter
    @Getter
    @AllArgsConstructor
    private static class Node {
        private Long id;
        private Double distance;
    }
}
