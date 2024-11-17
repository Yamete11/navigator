package org.example.ui;

import org.example.model.City;
import org.example.model.CityConnection;
import org.example.service.CityConnectionService;

import java.util.HashSet;
import java.util.Set;

public class GraphDrawer {

    GraphSet graphSet;
    private static final int MAP_HEIGHT = 40;
    private static final int MAP_WIDTH = 40; // line has 80 characters

    public GraphDrawer(CityConnectionService cityConnectionService) {
        this.graphSet = new GraphSet(cityConnectionService);
    }

    private Set<City> getUniqueCities(Set<CityConnection> uniqueCityConnections) {
        Set<City> uniqueCities = new HashSet<>();

        for (CityConnection connection : uniqueCityConnections) {
            uniqueCities.add(connection.getFirstCity());
            uniqueCities.add(connection.getSecondCity());
        }

        return uniqueCities;
    }

    public String draw() {
        String[][] map = initializeMap();
        drawCitiesAndConnections(map, graphSet);
        return buildMapString(map);
    }

    private static String[][] initializeMap() {
        String[][] map = new String[MAP_HEIGHT][MAP_WIDTH];
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                map[i][j] = "  ";
            }
        }
        return map;
    }

    private void drawCitiesAndConnections(String[][] map, GraphSet graphSet) {
        Set<CityConnection> connections = graphSet.getUniqueCityConnections();
        Set<City> cities = getUniqueCities(connections);

        for (City city : cities) {
            int x = (int) ((city.getX() / 1) / 10 * (MAP_WIDTH - 1));
            int y = (int) ((city.getY()/ 1) / 10 * (MAP_HEIGHT - 1));

            x = Math.max(0, Math.min(MAP_WIDTH - 1, x));
            y = Math.max(0, Math.min(MAP_HEIGHT - 1, y));

            map[y][x] = city.getTitle().charAt(0) + " ";
        }

        for (CityConnection connection : connections) {
            City cityStart = connection.getFirstCity();
            City cityEnd = connection.getSecondCity();

            int x1 = (int) ((cityStart.getX() / 1) / 10 * (MAP_WIDTH - 1));
            int y1 = (int) ((cityStart.getY() / 1) / 10 * (MAP_HEIGHT - 1));
            int x2 = (int) ((cityEnd.getX() / 1) / 10 * (MAP_WIDTH - 1));
            int y2 = (int) ((cityEnd.getY() / 1) / 10 * (MAP_HEIGHT - 1));

            x1 = Math.max(0, Math.min(MAP_WIDTH - 1, x1));
            y1 = Math.max(0, Math.min(MAP_HEIGHT - 1, y1));
            x2 = Math.max(0, Math.min(MAP_WIDTH - 1, x2));
            y2 = Math.max(0, Math.min(MAP_HEIGHT - 1, y2));

            drawLineOnMap(map, x1, y1, x2, y2);
        }
    }

    private void drawLineOnMap(String[][] map, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            if (x1 >= 0 && x1 < MAP_WIDTH && y1 >= 0 && y1 < MAP_HEIGHT) {
                if ("  ".equals(map[y1][x1])) {
                    map[y1][x1] = ". ";
                }
            }

            if (x1 == x2 && y1 == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    private String buildMapString(String[][] map) {
        StringBuilder sb = new StringBuilder();
        appendTopBorder(sb);
        appendMapContent(sb, map);
        appendBottomBorder(sb);
        return sb.toString();
    }

    private void appendTopBorder(StringBuilder sb) {
        sb.append("# ".repeat(MAP_WIDTH + 1));
        sb.append("#\n");
    }

    private void appendBottomBorder(StringBuilder sb) {
        sb.append("# ".repeat(MAP_WIDTH + 1));
        sb.append("#\n");
    }

    private void appendMapContent(StringBuilder sb, String[][] map) {
        for (int i = 0; i < MAP_HEIGHT; i++) {
            sb.append("# ");
            for (int j = 0; j < MAP_WIDTH; j++) {
                sb.append(map[i][j]);
            }
            sb.append("#\n");
        }
    }
}