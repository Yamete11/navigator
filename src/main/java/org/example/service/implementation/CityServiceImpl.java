package org.example.service.implementation;

import org.example.dao.CityMapper;
import org.example.dao.implementation.CityMapperImpl;
import org.example.model.City;
import org.example.model.CityConnection;
import org.example.model.Route;
import org.example.model.RouteCity;
import org.example.service.CityService;
import org.example.service.observer.CityEventType;
import org.example.service.observer.Observable;
import org.example.service.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService, Observable {

    private final CityMapper cityMapper;
    private final CityConnectionServiceImpl cityConnectionService;
    private final RouteCityServiceImpl routeCityService;
    private RouteServiceImpl routeService;
    private final List<Observer> observers = new ArrayList<>();

    public CityServiceImpl() {
        this.cityMapper = new CityMapperImpl();
        this.cityConnectionService = new CityConnectionServiceImpl();
        this.routeCityService = new RouteCityServiceImpl();
    }

    private RouteServiceImpl getRouteService() {
        if (routeService == null) {
            routeService = new RouteServiceImpl();
        }
        return routeService;
    }

    @Override
    public void create(City city) {
        try {
            cityMapper.create(city);
            notifyObservers(CityEventType.CITY_ADDED, city);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Validation failed: " + e.getMessage(), e);
        }

    }

    @Override
    public Optional<City> getById(Long id) {
        return cityMapper.getById(id);
    }

    @Override
    public List<City> findAll() {
        return cityMapper.findAll();
    }

    @Override
    public void update(City city) {
        Optional<City> optionalCity = cityMapper.getById(city.getId());
        if (optionalCity.isPresent()) {
            City existingCity = optionalCity.get();

            boolean coordinatesChanged =
                    existingCity.getX() != city.getX() || existingCity.getY() != city.getY();

            try {
                cityMapper.update(city);
                notifyObservers(CityEventType.CITY_UPDATED, city);

                if (coordinatesChanged) {
                    List<CityConnection> connections = cityConnectionService.getCityConnectionsByCityId(city.getId());
                    for (CityConnection connection : connections) {
                        cityConnectionService.update(connection);
                    }

                    List<Route> routeList = getRouteService().findAll();
                    for (Route route : routeList) {
                        routeCityService.deleteByRouteId(route.getId());
                    }
                    for (Route route : routeList) {
                        getRouteService().update(route);
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Validation failed: " + e.getMessage(), e);
            }
        }
    }


    @Override
    public void deleteById(Long id) {
        Optional<City> cityOpt = getById(id);
        cityOpt.ifPresent(city -> {
            cityConnectionService.deleteByCityId(id);
            List<Route> routes = getRouteService().getRoutesByCityId(id);
            for (Route route : routes) {
                getRouteService().deleteById(route.getId());
            }
            cityMapper.deleteById(id);
            notifyObservers(CityEventType.CITY_DELETED, city);
        });
    }

    @Override
    public Optional<City> getCityByTitle(String title) {
        return cityMapper.getByTitle(title);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(CityEventType eventType, City city) {
        for (Observer observer : observers) {
            observer.update(eventType, city);
        }
    }
}
