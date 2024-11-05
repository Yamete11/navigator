package org.example.service.implementation;

import org.example.dao.RouteMapper;
import org.example.dao.implementation.RouteMapperImpl;
import org.example.model.Route;
import org.example.service.RouteService;

import java.util.List;
import java.util.Optional;

public class RouteServiceImpl implements RouteService {

    private final RouteMapper routeMapper;

    public RouteServiceImpl() {
        this.routeMapper = new RouteMapperImpl();
    }

    @Override
    public Route create(Route route) {
        return routeMapper.create(route);
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
        routeMapper.deleteById(id);
    }
}
