package org.example.service.implementation;

import org.example.dao.RouteCityMapper;
import org.example.dao.implementation.RouteCityMapperImpl;
import org.example.model.RouteCity;
import org.example.service.RouteCityService;

import java.util.List;
import java.util.Optional;

public class RouteCityServiceImpl implements RouteCityService {

    private final RouteCityMapper routeCityMapper;

    public RouteCityServiceImpl() {
        this.routeCityMapper = new RouteCityMapperImpl();
    }

    @Override
    public RouteCity create(RouteCity routeCity) {
        return routeCityMapper.create(routeCity);
    }

    @Override
    public Optional<RouteCity> getById(Long id) {
        return routeCityMapper.getById(id);
    }

    @Override
    public List<RouteCity> findAll() {
        return routeCityMapper.findAll();
    }

    @Override
    public void update(RouteCity routeCity) {
        routeCityMapper.update(routeCity);
    }

    @Override
    public void deleteById(Long id) {
        routeCityMapper.deleteById(id);
    }
}
