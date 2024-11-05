package org.example.service.implementation;

import org.example.dao.CityConnectionMapper;
import org.example.dao.CityMapper;
import org.example.dao.implementation.CityConnectionMapperImpl;
import org.example.dao.implementation.CityMapperImpl;
import org.example.model.CityConnection;
import org.example.service.CityConnectionService;

import java.util.List;
import java.util.Optional;

public class CityConnectionServiceImpl implements CityConnectionService {

    private final CityConnectionMapper cityConnectionMapper;

    public CityConnectionServiceImpl() {
        this.cityConnectionMapper = new CityConnectionMapperImpl();
    }

    @Override
    public CityConnection create(CityConnection cityConnection) {
        return cityConnectionMapper.create(cityConnection);
    }

    @Override
    public Optional<CityConnection> getById(Long id) {
        return cityConnectionMapper.getById(id);
    }

    @Override
    public List<CityConnection> findAll() {
        return cityConnectionMapper.findAll();
    }

    @Override
    public void update(CityConnection cityConnection) {
        cityConnectionMapper.update(cityConnection);
    }

    @Override
    public void deleteById(Long id) {
        cityConnectionMapper.deleteById(id);
    }
}