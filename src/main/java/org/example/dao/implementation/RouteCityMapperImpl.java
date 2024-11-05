package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.config.ConnectionManager;
import org.example.dao.RouteCityMapper;
import org.example.model.RouteCity;

import java.util.List;
import java.util.Optional;

public class RouteCityMapperImpl implements RouteCityMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public RouteCityMapperImpl() {
        this.sqlSessionFactory = ConnectionManager.getInstance().getSessionFactory();
    }

    @Override
    public RouteCity create(RouteCity entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteCityMapper routeCityMapper = session.getMapper(RouteCityMapper.class);
            routeCityMapper.create(entity);
            session.commit();
            return entity;
        }
    }

    @Override
    public Optional<RouteCity> getById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteCityMapper routeCityMapper = session.getMapper(RouteCityMapper.class);
            return routeCityMapper.getById(id);
        }
    }

    @Override
    public List<RouteCity> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteCityMapper routeCityMapper = session.getMapper(RouteCityMapper.class);
            return routeCityMapper.findAll();
        }
    }

    @Override
    public void update(RouteCity entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteCityMapper routeCityMapper = session.getMapper(RouteCityMapper.class);
            routeCityMapper.update(entity);
            session.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteCityMapper routeCityMapper = session.getMapper(RouteCityMapper.class);
            routeCityMapper.deleteById(id);
            session.commit();
        }
    }
}
