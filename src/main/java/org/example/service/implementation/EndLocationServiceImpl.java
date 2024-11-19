package org.example.service.implementation;

import org.example.dao.EndLocationMapper;
import org.example.dao.implementation.EndLocationMapperImpl;
import org.example.model.EndLocation;
import org.example.service.EndLocationService;

import java.util.List;
import java.util.Optional;

public class EndLocationServiceImpl implements EndLocationService {

    private final EndLocationMapper endLocationMapper;


    public EndLocationServiceImpl() {
        this.endLocationMapper = new EndLocationMapperImpl();
    }

    @Override
    public void create(EndLocation entity) {
        endLocationMapper.create(entity);
    }

    @Override
    public Optional<EndLocation> getById(Long id) {
        return endLocationMapper.getById(id);
    }

    @Override
    public List<EndLocation> findAll() {
        return endLocationMapper.findAll();
    }

    @Override
    public void update(EndLocation entity) {
        endLocationMapper.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        endLocationMapper.deleteById(id);
    }

    @Override
    public void deleteByCityId(Long id) {
        endLocationMapper.deleteByCityId(id);
    }
}
