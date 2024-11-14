package org.example.ui;

import org.example.model.City;
import org.example.model.CityConnection;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GraphSet implements ICityConnectionChecker{

    private List<CityConnection> connections;
    private final Set<City> uniqCities;
    private final Set<CityConnection> uniqueCityConnections;

    public GraphSet(INavigator navigator) {
        this.connections = navigator.findAllCityConnections();
        this.uniqueCityConnections = uniqueCityConnection(connections);
        this.uniqCities = Set.of();
    }

    public Set<CityConnection> uniqueCityConnection(List<CityConnection> connections) {
        Set<CityConnection> uniqueCityConnections =
                new TreeSet<>((c1, c2) -> isCityConnectionIdDuplicate(c1,c2)?0:1);
        uniqueCityConnections.addAll(connections);
        return uniqueCityConnections;
    }

    public Set<City> getUniqueCities() {
        return uniqCities;
    }

    public static void main(String[] args) {
        INavigator navigator = new NavigatorMockImpl();
        GraphSet graphSet = new GraphSet(navigator);

        System.out.println("Unikalne połączenia:");
        for (CityConnection connection : graphSet.uniqueCityConnections) {
            System.out.println(connection);
        }

        System.out.println("Unikalne miasta:");
        for (City city : graphSet.getUniqueCities()) {
            System.out.println(city);
        }
    }
}
