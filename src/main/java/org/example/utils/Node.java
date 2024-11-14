package org.example.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Node {
    private Long id;
    private Map<Node, Double> neighbors;

    public Node(Long id) {
        this.id = id;
        neighbors = new HashMap<>();
    }

    public void addNeighbor(Node neighbor, double distance) {
        neighbors.put(neighbor, distance);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNeighbors(Map<Node, Double> neighbors) {
        this.neighbors = neighbors;
    }

    public boolean containsNeighbor(Long neighbor) {
        return neighbors.keySet().stream().map(Node::getId).noneMatch(neighbor::equals);
    }
}
