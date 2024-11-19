package org.example.utils.implementation;

import org.example.dao.implementation.CityConnectionMapperImpl;
import org.example.dao.implementation.CityMapperImpl;
import org.example.model.City;
import org.example.model.RouteCity;
import org.example.service.CityService;
import org.example.service.implementation.CityConnectionServiceImpl;
import org.example.service.implementation.CityServiceImpl;
import org.example.service.implementation.RouteCityServiceImpl;
import org.example.utils.*;

import java.util.*;

public class AStarStrategy implements RouteFindingStrategy {
    private final Graph graph;
    private final Navigator navigator;
    private final CityService cityService;
    private final HeuristicCache heuristicCache;

    public AStarStrategy() {
        graph = GraphLoader.getGraph();
        navigator = new CityNavigator();
        cityService = new CityServiceImpl(new CityMapperImpl(), new CityConnectionServiceImpl(new CityConnectionMapperImpl()),new RouteCityServiceImpl());
        this.heuristicCache = HeuristicCache.getInstance();
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
        fScore.put(startNode, heuristicCache.getHeuristic(start, end));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(endNode)) {
                return reconstructPath(current, cameFrom, cityService);
            }

            for (Map.Entry<Node, Double> neighborEntry : current.getNeighbors().entrySet()) {
                Long neighborId = neighborEntry.getKey().getId();
                Node neighbor = graph.getNode(neighborId);
                double tentativeGScore = gScore.getOrDefault(current, Double.MAX_VALUE) + neighborEntry.getValue();

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);

                    double heuristic = heuristicCache.getHeuristic(cityService.getById(neighbor.getId()).get(), end);
                    fScore.put(neighbor, tentativeGScore + heuristic);

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return Collections.emptyList();
    }
}
