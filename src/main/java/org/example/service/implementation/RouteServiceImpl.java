package org.example.service.implementation;

import org.example.dao.RouteMapper;
import org.example.dao.implementation.RouteMapperImpl;
import org.example.model.Route;
import org.example.model.RouteCity;
import org.example.service.RouteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteServiceImpl implements RouteService {

    private final RouteMapper routeMapper;
    private final StartLocationServiceImpl startLocationService;
    private final EndLocationServiceImpl endLocationService;
    private final RouteCityServiceImpl routeCityService;

    public RouteServiceImpl() {
        this.routeMapper = new RouteMapperImpl();
        this.startLocationService = new StartLocationServiceImpl();
        this.endLocationService = new EndLocationServiceImpl();
        this.routeCityService = new RouteCityServiceImpl();
    }

    @Override
    public void create(Route route) {
        startLocationService.create(route.getStartLocation());
        endLocationService.create(route.getEndLocation());
        routeMapper.create(route);
        //Here we have to get the list of RouteCity and calculate the distance
        /*List<RouteCity> routeCities = new ArrayList<>();

        RouteCity routeCity1 = new RouteCity();
        routeCity1.setCity(route.getStartLocation().getCity());
        routeCity1.setRoute(route);
        routeCity1.setOrderIndex(1L);

        RouteCity routeCity2 = new RouteCity();
        routeCity2.setCity(route.getEndLocation().getCity());
        routeCity2.setRoute(route);
        routeCity2.setOrderIndex(2L);

        routeCities.add(routeCity1);
        routeCities.add(routeCity2);
        routeCityService.createBatch(routeCities);*/
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
        routeMapper.update(route);
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
