package org.example.utils;

import org.example.model.City;
import org.example.model.RouteCity;
import org.example.service.CityService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public interface RouteFindingStrategy {
    List<RouteCity> findRoute(City start, City end);

    default List<RouteCity> reconstructPath(Node end, Map<Node, Node> previousNodes, CityService cityService) {
        List<RouteCity> path = new ArrayList<>();
        Node current = end;
        AtomicLong order = new AtomicLong(0);

        while (current != null) {
            City currentCity = cityService.findAll().get(Math.toIntExact(current.getId()) - 1);
            path.add(new RouteCity(currentCity, null, 0L));
            current = previousNodes.get(current);
        }
        Collections.reverse(path);

        path.forEach(x -> x.setOrderIndex(order.getAndIncrement()));
        return path;
    }
}
