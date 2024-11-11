package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.config.ConnectionManager;
import org.example.dao.CityConnectionMapper;
import org.example.dao.StartLocationMapper;
import org.example.model.StartLocation;

import java.util.List;
import java.util.Optional;

public class StartLocationMapperImpl implements StartLocationMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public StartLocationMapperImpl() {
        this.sqlSessionFactory = ConnectionManager.getInstance().getSessionFactory();
    }

    @Override
    public void create(StartLocation entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StartLocationMapper startLocationMapper = session.getMapper(StartLocationMapper.class);
            startLocationMapper.create(entity);
            session.commit();
        }
    }

    @Override
    public Optional<StartLocation> getById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StartLocationMapper startLocationMapper = session.getMapper(StartLocationMapper.class);
            return startLocationMapper.getById(id);
        }
    }

    @Override
    public List<StartLocation> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StartLocationMapper startLocationMapper = session.getMapper(StartLocationMapper.class);
            return startLocationMapper.findAll();
        }
    }

    @Override
    public void update(StartLocation entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StartLocationMapper startLocationMapper = session.getMapper(StartLocationMapper.class);
            startLocationMapper.update(entity);
            session.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StartLocationMapper startLocationMapper = session.getMapper(StartLocationMapper.class);
            startLocationMapper.deleteById(id);
            session.commit();
        }
    }

    @Override
    public void deleteByCityId(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StartLocationMapper startLocationMapper = session.getMapper(StartLocationMapper.class);
            startLocationMapper.deleteByCityId(id);
            session.commit();
        }
    }
}
