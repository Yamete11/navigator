package org.example.service.implementation;

import org.example.dao.StartLocationMapper;
import org.example.dao.implementation.StartLocationMapperImpl;
import org.example.model.StartLocation;
import org.example.service.StartLocationService;

import java.util.List;
import java.util.Optional;

public class StartLocationServiceImpl implements StartLocationService {

    private final StartLocationMapper startLocationMapper;

    public StartLocationServiceImpl() {
        this.startLocationMapper = new StartLocationMapperImpl();
    }

    @Override
    public void create(StartLocation entity) {
        startLocationMapper.create(entity);
    }

    @Override
    public Optional<StartLocation> getById(Long id) {
        return startLocationMapper.getById(id);
    }

    @Override
    public List<StartLocation> findAll() {
        return startLocationMapper.findAll();
    }

    @Override
    public void update(StartLocation entity) {
        startLocationMapper.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        startLocationMapper.deleteById(id);
    }

    @Override
    public void deleteByCityId(Long id) {
        startLocationMapper.deleteByCityId(id);
    }
}
