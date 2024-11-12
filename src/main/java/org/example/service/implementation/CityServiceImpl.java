package org.example.service.implementation;

import org.example.dao.CityMapper;
import org.example.dao.implementation.CityMapperImpl;
import org.example.model.City;
import org.example.model.Route;
import org.example.service.CityService;

import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;
    private final CityConnectionServiceImpl cityConnectionService;
    private final RouteServiceImpl routeService;



    public CityServiceImpl() {
        this.cityMapper = new CityMapperImpl();
        this.cityConnectionService = new CityConnectionServiceImpl();
        this.routeService = new RouteServiceImpl();

    }


    @Override
    public void create(City city) {
        cityMapper.create(city);
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
        cityMapper.update(city);
    }

    @Override
    public void deleteById(Long id) {
        cityConnectionService.deleteByCityId(id);
        List<Route> routes = routeService.getRoutesByCityId(id);
        for (Route route : routes) {
            routeService.deleteById(route.getId());
        }
        cityMapper.deleteById(id);
    }

    @Override
    public Optional<City> getCityByTitle(String title) {
        return cityMapper.getByTitle(title);
    }

}
