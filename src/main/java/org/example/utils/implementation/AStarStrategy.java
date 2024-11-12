package org.example.utils.implementation;

import org.example.model.City;
import org.example.model.RouteCity;
import org.example.service.CityService;
import org.example.service.implementation.CityServiceImpl;
import org.example.utils.*;

import java.util.*;

public class AStarStrategy implements RouteFindingStrategy {
    private final Graph graph;
    private final Navigator navigator;
    private final CityService cityService;

    public AStarStrategy() {
        graph = GraphLoader.getGraph();
        navigator = new CityNavigator();
        cityService = new CityServiceImpl();
    }

    private Double heuristic(Node a, Node b) {
        Optional<City> cityA = cityService.getById(a.getId());
        Optional<City> cityB = cityService.getById(b.getId());
        return navigator.calculateDistance(cityA, cityB);
    }

    @Override
    public List<RouteCity> findRoute(City start, City end) {
        Node startNode = graph.getNode(start.getId());
        Node endNode = graph.getNode(end.getId());

        if (startNode == null || endNode == null) {
            throw new IllegalArgumentException("Start and end nodes are not found in graph");
        }

        Map<Node, Node> cameFrom = new HashMap<>();
        Map<Node, Double> gScore = new HashMap<>();
        Map<Node, Double> fScore = new HashMap<>();
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(fScore::get));

        for (Node node : graph.getNodes().values()) {
            gScore.put(node, Double.MAX_VALUE);
            fScore.put(node, Double.MAX_VALUE);
        }

        gScore.put(startNode, 0.0d);
        fScore.put(startNode, heuristic(startNode, endNode));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(endNode)) {
                return reconstructPath(current, cameFrom, cityService);
            }

            for (Map.Entry<Node, Double> neighborEntry : current.getNeighbors().entrySet()) {
                Long neighborId = neighborEntry.getKey().getId();
                Node neighbor = graph.getNode(neighborId);
                Double tentativeGScore = gScore.get(current) + neighborEntry.getValue();

                if (tentativeGScore < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, endNode));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return Collections.emptyList();
    }
}
