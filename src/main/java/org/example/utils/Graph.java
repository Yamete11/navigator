package org.example.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Graph {
    private final Map<Long, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    public Node getNode(Long id) {
        return nodes.get(id);
    }

    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }

    public void addEdge(Long startId, Long endId, double distance) {
        Node start = nodes.computeIfAbsent(startId, Node::new);
        Node end = nodes.computeIfAbsent(endId, Node::new);
        if (start.containsNeighbor(endId)) {
            start.addNeighbor(end, distance);
        }
        if (end.containsNeighbor(startId)) {
            end.addNeighbor(start, distance);
        }
    }

    public Map<Long, Node> getNodes() {
        return nodes;
    }
}
