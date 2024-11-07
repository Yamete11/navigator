package org.example.ui;

import org.example.model.CityConnection;

import java.util.List;

@FunctionalInterface
public interface INavigator {
    List<CityConnection> findAllCityConnections();
}
