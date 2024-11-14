package org.example.service.implementation;

import org.example.dao.RouteMapper;
import org.example.dao.implementation.RouteMapperImpl;
import org.example.model.City;
import org.example.model.CityConnection;
import org.example.model.Route;
import org.example.model.RouteCity;
import org.example.service.RouteService;
import org.example.utils.DistanceCalculator;
import org.example.utils.implementation.AStarStrategy;
import org.example.utils.implementation.CityNavigator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RouteServiceImpl implements RouteService {

    private final RouteMapper routeMapper;
    private final StartLocationServiceImpl startLocationService;
    private final EndLocationServiceImpl endLocationService;
    private final RouteCityServiceImpl routeCityService;
    private final CityServiceImpl cityService;
    private final CityConnectionServiceImpl cityConnection;
    private final CityNavigator cityNavigator;

    public RouteServiceImpl() {
        this.routeMapper = new RouteMapperImpl();
        this.startLocationService = new StartLocationServiceImpl();
        this.endLocationService = new EndLocationServiceImpl();
        this.routeCityService = new RouteCityServiceImpl();
        this.cityService = new CityServiceImpl();
        this.cityConnection = new CityConnectionServiceImpl();
        this.cityNavigator = new CityNavigator();
    }

    @Override
    public void create(Route route) {
        startLocationService.create(route.getStartLocation());
        endLocationService.create(route.getEndLocation());
        this.cityNavigator.setStrategy(new AStarStrategy());
        List<RouteCity> routeCities = cityNavigator.findRoute(route.getStartLocation().getCity(), route.getEndLocation().getCity());
        route.setTotalDistance(DistanceCalculator.calculateTotalDistance(routeCities));
        routeMapper.create(route);
        routeCities.forEach(routeCity -> routeCity.setRoute(route));
        routeCityService.createBatch(routeCities);
    }

    @Override
    public Optional<Route> getById(Long id) {
        return routeMapper.getById(id);
    }

    @Override
    public List<Route> findAll() {
        return routeMapper.findAll();
    }

    @Override
    public void update(Route route) {
        this.cityNavigator.setStrategy(new AStarStrategy());
        List<RouteCity> routeCities = cityNavigator.findRoute(route.getStartLocation().getCity(), route.getEndLocation().getCity());
        route.setTotalDistance(DistanceCalculator.calculateTotalDistance(routeCities));
        routeMapper.update(route);
        routeCities.forEach(routeCity -> routeCity.setRoute(route));
        routeCityService.createBatch(routeCities);
    }

    @Override
    public void deleteById(Long id) {
        Route route = routeMapper.getById(id).get();
        routeCityService.deleteByRouteId(id);
        routeMapper.deleteById(id);
        startLocationService.deleteById(route.getStartLocation().getId());
        endLocationService.deleteById(route.getEndLocation().getId());
    }

    @Override
    public List<Route> getRoutesByCityId(Long id) {
        return routeMapper.getRoutesByCityId(id);
    }
}
