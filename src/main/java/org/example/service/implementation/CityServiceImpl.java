package org.example.service.implementation;

import org.example.dao.CityMapper;
import org.example.dao.implementation.CityMapperImpl;
import org.example.model.City;
import org.example.service.CityService;

import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;

    public CityServiceImpl() {
        this.cityMapper = new CityMapperImpl();
    }


    @Override
    public City create(City city) {
        return cityMapper.create(city);
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
        cityMapper.deleteById(id);
    }
}
