package org.example.utils.implementation;

import org.example.model.City;
import org.example.model.RouteCity;
import org.example.service.CityService;
import org.example.service.implementation.CityServiceImpl;
import org.example.utils.*;

import java.util.*;

public class DijkstraStrategy implements RouteFindingStrategy {
    private final Graph graph;
    private final Navigator navigator;
    private final CityService cityService;

    public DijkstraStrategy() {
        this.graph = GraphLoader.getGraph();
        this.navigator = new CityNavigator();
        this.cityService = new CityServiceImpl();
    }

    @Override
    public List<RouteCity> findRoute(City start, City end) {
        Node startNode = graph.getNode(start.getId());
        Node endNode = graph.getNode(end.getId());

        if (startNode == null || endNode == null) {
            throw new IllegalArgumentException("Start or end node not found in the graph");
        }

        Map<Node, Double> distances = new HashMap<>();
        Map<Node, Node> previousNodes = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Node node : graph.getNodes().values()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(startNode, 0.0d);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.equals(endNode)) break;

            for (Map.Entry<Node, Double> neighborEntry : currentNode.getNeighbors().entrySet()) {
                Long neighborNodeId = neighborEntry.getKey().getId();
                Node neighborNode = graph.getNode(neighborNodeId);
                double distance = navigator.calculateDistance(cityService.getById(currentNode.getId()), cityService.getById(neighborNode.getId()));
                double distanceThroughRoute = distances.get(currentNode) + distance;

                if (distanceThroughRoute < distances.get(neighborNode)) {
                    distances.put(neighborNode, distanceThroughRoute);
                    previousNodes.put(neighborNode, currentNode);
                    queue.add(neighborNode);
                }
            }
        }

        return reconstructPath(endNode, previousNodes, cityService);
    }
}
