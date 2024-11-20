package org.example.utils.implementation;

import lombok.Setter;
import org.example.dao.implementation.CityConnectionMapperImpl;
import org.example.dao.implementation.CityMapperImpl;
import org.example.model.City;
import org.example.model.RouteCity;
import org.example.service.CityConnectionService;
import org.example.service.CityService;
import org.example.service.implementation.CityConnectionServiceImpl;
import org.example.service.implementation.CityServiceImpl;
import org.example.service.implementation.RouteCityServiceImpl;
import org.example.utils.Navigator;
import org.example.utils.RouteFindingStrategy;

import java.util.List;

public class CityNavigator implements Navigator {
    private final CityService cityService;
    private final CityConnectionService cityConnectionService;
    @Setter
    private RouteFindingStrategy strategy;

    public CityNavigator() {
        this.cityService = new CityServiceImpl(new CityMapperImpl(), new CityConnectionServiceImpl(new CityConnectionMapperImpl()), new RouteCityServiceImpl());
        this.cityConnectionService = new CityConnectionServiceImpl(new CityConnectionMapperImpl());
    }

    @Override
    public List<RouteCity> findRoute(City start, City end) {
        if (strategy == null) throw new IllegalStateException("Strategy not initialized");
        return strategy.findRoute(start, end);
    }

    @Override
    public List<RouteCity> findRoute(String firstCity, String secondCity) {
        if (strategy == null) throw new IllegalStateException("Strategy not initialized");
        City start = cityService.getCityByTitle(firstCity).get();
        City end = cityService.getCityByTitle(secondCity).get();
        return strategy.findRoute(start, end);
    }
}
