package org.example.ui;

import org.example.model.CityConnection;

import java.util.List;

public class GraphDrawer {

    private INavigator navigator;

    public GraphDrawer(INavigator navigator) {
        this.navigator = navigator;
    }

    private static final int MAP_HEIGHT = 2;
    private static final int MAP_WIDTH = 2;

    public void draw() {
        List<CityConnection> connections = navigator.findAllCityConnections();

        String[][] map = new String[MAP_HEIGHT][MAP_WIDTH];
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                map[i][j] = "  ";
            }
        }
        printMap(map);
    }

    private void printMap(String [][] map) {
        // Print top border
        for (int j = 0; j < MAP_WIDTH + 2; j++) {
            System.out.print("# ");
        }
        System.out.println();

        // Print map with borders
        for (int i = 0; i < MAP_HEIGHT; i++) {
            System.out.print("# "); // Left border
            for (int j = 0; j < MAP_WIDTH; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("# "); // Right border
        }

        // Print bottom border
        for (int j = 0; j < MAP_WIDTH + 2; j++) {
            System.out.print("# ");
        }
        System.out.println();
    }

}
