package org.example.ui;

import org.example.model.CityConnection;

import java.util.List;

public class GraphDrawer {

    private final INavigator navigator;
    private static final int MAP_HEIGHT = 2;
    private static final int MAP_WIDTH = 2;

    public GraphDrawer(INavigator navigator) {
        this.navigator = navigator;
    }

    public String draw() {
        List<CityConnection> connections = navigator.findAllCityConnections();
        String[][] map = initializeMap();
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

    private String buildMapString(String[][] map) {
        StringBuilder sb = new StringBuilder();
        appendTopBorder(sb);
        appendMapContent(sb, map);
        appendBottomBorder(sb);
        return sb.toString();
    }

    private void appendTopBorder(StringBuilder sb) {
        for (int j = 0; j < MAP_WIDTH + 2; j++) {
            sb.append("# ");
        }
        sb.append("\n");
    }

    private void appendBottomBorder(StringBuilder sb) {
        for (int j = 0; j < MAP_WIDTH + 2; j++) {
            sb.append("# ");
        }
        sb.append("\n");
    }

    private void appendMapContent(StringBuilder sb, String[][] map) {
        for (int i = 0; i < MAP_HEIGHT; i++) {
            sb.append("# ");
            for (int j = 0; j < MAP_WIDTH; j++) {
                sb.append(map[i][j]);
            }
            sb.append("# \n");
        }
    }

}
