package org.example.service.implementation;

import org.example.dao.RouteMapper;
import org.example.model.CityConnection;
import org.example.model.Route;
import org.example.model.RouteCity;
import org.example.service.EndLocationService;
import org.example.service.RouteCityService;
import org.example.service.RouteService;
import org.example.service.StartLocationService;
import org.example.service.observer.RouteEventType;
import org.example.service.observer.RouteObserver;
import org.example.utils.DistanceCalculator;
import org.example.utils.implementation.AStarStrategy;
import org.example.utils.implementation.CityNavigator;
import org.example.utils.implementation.DijkstraStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteServiceImpl implements RouteService {

    private final RouteMapper routeMapper;
    private final StartLocationService startLocationService;
    private final EndLocationService endLocationService;
    private final RouteCityService routeCityService;
    private final CityNavigator cityNavigator;

    private final List<RouteObserver> observers = new ArrayList<>();

    public RouteServiceImpl(RouteMapper routeMapper, StartLocationService startLocationService, EndLocationService endLocationService, RouteCityService routeCityService, CityNavigator cityNavigator) {
        this.routeMapper = routeMapper;
        this.startLocationService = startLocationService;
        this.endLocationService = endLocationService;
        this.routeCityService = routeCityService;
        this.cityNavigator = cityNavigator;
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

        notifyObservers(RouteEventType.ROUTE_ADDED, route);
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
        this.cityNavigator.setStrategy(new DijkstraStrategy());
        List<RouteCity> routeCities = cityNavigator.findRoute(route.getStartLocation().getCity(), route.getEndLocation().getCity());
        route.setTotalDistance(DistanceCalculator.calculateTotalDistance(routeCities));
        routeMapper.update(route);
        routeCities.forEach(routeCity -> routeCity.setRoute(route));
        routeCityService.createBatch(routeCities);

        notifyObservers(RouteEventType.ROUTE_UPDATED, route);
    }

    @Override
    public void deleteById(Long id) {
        Route route = routeMapper.getById(id).get();
        routeCityService.deleteByRouteId(id);
        routeMapper.deleteById(id);
        startLocationService.deleteById(route.getStartLocation().getId());
        endLocationService.deleteById(route.getEndLocation().getId());

        notifyObservers(RouteEventType.ROUTE_DELETED, route);
    }

    @Override
    public List<Route> getRoutesByCityId(Long id) {
        return routeMapper.getRoutesByCityId(id);
    }

    @Override
    public Route checkIfRouteExists(Long city1Id, Long city2Id) {
        return routeMapper.checkIfRouteExists(city1Id, city2Id);
    }

    @Override
    public List<CityConnection> getCityConnectionsByRouteId(Long routeId) {
        return routeMapper.getCityConnectionsByRouteId(routeId);
    }

    @Override
    public void addObserver(RouteObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(RouteObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(RouteEventType eventType, Route route) {
        for (RouteObserver observer : observers) {
            observer.update(eventType, route);
        }
    }
}
