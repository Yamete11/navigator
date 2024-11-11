package org.example.utils.implementation;

import org.example.model.*;
import org.example.service.*;
import org.example.service.implementation.*;
import org.example.utils.Navigator;

import java.util.*;

public class CityNavigator implements Navigator {
    private CityService cityService;
    private CityConnectionService cityConnectionService;
    private RouteCityService routeCityService;
    private StartLocationService startLocationService;
    private EndLocationService endLocationService;
    private RouteService routeService;

    public CityNavigator() {
        this.cityService = new CityServiceImpl();
        this.cityConnectionService = new CityConnectionServiceImpl();
        this.routeCityService = new RouteCityServiceImpl();
        this.startLocationService = new StartLocationServiceImpl();
        this.endLocationService = new EndLocationServiceImpl();
        this.routeService = new RouteServiceImpl();
    }

    @Override
    public Route findRoute(City start, City end) {
        return getRoute(start, end);
    }

    @Override
    public Route findRoute(String firstCity, String secondCity) {
        City start = cityService.getCityByTitle(firstCity).get();
        City end = cityService.getCityByTitle(secondCity).get();
        return getRoute(start, end);
    }

    private Route getRoute(City start, City end) {
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
                return buildRoute(distances.get(end.getId()), start, end, previousCities);
            }

            if (visited.contains(currentCityId)) continue;
            visited.add(currentCityId);

            List<CityConnection> neighbors = cityConnectionService.getCityConnectionsByCityId(currentCityId);
            for (CityConnection connection : neighbors) {
                Long neighborCityId = Objects.equals(connection.getFirstCity().getId(), currentCityId) ? connection.getSecondCity().getId() : connection.getFirstCity().getId();
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

    private Route buildRoute(Double distance, City start, City end, Map<Long, Long> previousCities) {
        Route result = new Route();
        result.setDistance(distance);

        StartLocation startLocation = new StartLocation(start);
        startLocationService.create(startLocation);
        result.setStartLocation(startLocation);

        EndLocation endLocation = new EndLocation(end);
        endLocationService.create(endLocation);
        result.setEndLocation(endLocation);

        routeService.create(result);

        for (Long at = end.getId(), counter = 0L; at != null; at = previousCities.get(at), counter++) {
            City currentCity = cityService.findAll().get((int) (at - 1));
            RouteCity routeCity = new RouteCity(currentCity, result, counter);
            routeCityService.create(routeCity);
        }

        return result;
    }

    @Override
    public Double calculateDistance(CityConnection cityConnection) {
        City city1 = cityConnection.getFirstCity();
        City city2 = cityConnection.getSecondCity();
        return Math.sqrt(Math.pow(city2.getX() - city1.getX(), 2) + Math.pow(city2.getY() - city1.getY(), 2));
    }

    @Override
    public List<CityConnection> findAllCityConnections() {
        return cityConnectionService.findAll();
    }

    private static class Node {
        private Long id;
        private Double distance;

        public Node(Long id, Double distance) {
            this.id = id;
            this.distance = distance;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }
    }
}
