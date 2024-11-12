package org.example.utils;

import org.example.model.City;

import java.util.HashMap;
import java.util.Map;

public class HeuristicCache {
    private static HeuristicCache instance;

    private Map<String, Double> heuristicCache;

    private HeuristicCache() {
        heuristicCache = new HashMap<>();
    }

    public static synchronized HeuristicCache getInstance() {
        if (instance == null) {
            instance = new HeuristicCache();
        }
        return instance;
    }

    public double getHeuristic(City start, City goal) {
        String key = generateKey(start, goal);

        if (!heuristicCache.containsKey(key)) {
            double heuristic = DistanceCalculator.calculateDistance(start, goal);
            heuristicCache.put(key, heuristic);
        }
        return heuristicCache.get(key);
    }

    private String generateKey(City start, City goal) {
        return start.getTitle() + " -> " + goal.getTitle();
    }

    public void clearCache() {
        heuristicCache.clear();
    }
}
