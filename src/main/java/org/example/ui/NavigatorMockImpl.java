package org.example.ui;

import org.example.model.City;
import org.example.model.CityConnection;

import java.util.List;
import java.util.Set;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NavigatorMockImpl implements INavigator, ICityConnectionChecker {

    @Override
    public List<CityConnection> findAllCityConnections() {

        CityConnection cityConnection1 = new CityConnection(1L, 2.0,
                new City(1L, "Wro", 2.0, 4.0),
                new City(2L, "Poz", 2.0, 6.0));

        CityConnection cityConnection2 = new CityConnection(2L, 6.0,
                new City(3L, "Szcz", 8.0, 6.0),
                new City(2L, "Poz", 2.0, 6.0));

        CityConnection cityConnection3 = new CityConnection(3L, 6.0,
                new City(3L, "Szcz", 8.0, 6.0),
                new City(4L, "Rzesz", 5.0, 8.0));

        CityConnection cityConnection4 = new CityConnection(4L, 6.0,
                new City(3L, "Szcz", 8.0, 6.0),
                new City(2L, "Poz", 2.0, 6.0));

        CityConnection cityConnection5 = new CityConnection(5L, 6.0,
                new City(2L, "Poz", 2.0, 6.0),
                new City(3L, "Szcz", 8.0, 6.0));

        return List.of(cityConnection1, cityConnection2, cityConnection3,
                cityConnection4, cityConnection5);
    }

    @Override
    public Set<CityConnection> uniqueCityConnection(List<CityConnection> connections) {
        Set<CityConnection> uniqueConnections = new HashSet<>();
        for (CityConnection connection : connections) {
            boolean isDuplicate = false;
            for (CityConnection unique : uniqueConnections) {
                if (isCityConnectionIdDuplicate(connection, unique)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueConnections.add(connection);
            }
        }
        return uniqueConnections;
    }

    public static void main(String[] args) {
        NavigatorMockImpl navigator = new NavigatorMockImpl();

        List<CityConnection> connections = navigator.findAllCityConnections();

        boolean isDuplicate = navigator.isCityConnectionIdDuplicate(connections.get(4), connections.get(3));

        System.out.println("Czy połączenie jest duplikatem? " + isDuplicate);
        System.out.println(connections.get(4));
        System.out.println(connections.get(3));
    }
}


