package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.config.ConnectionManager;
import org.example.dao.RouteMapper;
import org.example.model.Route;

import java.util.List;
import java.util.Optional;

public class RouteMapperImpl implements RouteMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public RouteMapperImpl() {
        this.sqlSessionFactory = ConnectionManager.getInstance().getSessionFactory();
    }

    @Override
    public void create(Route entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteMapper routeMapper = session.getMapper(RouteMapper.class);
            routeMapper.create(entity);
            session.commit();
        }
    }

    @Override
    public Optional<Route> getById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteMapper routeMapper = session.getMapper(RouteMapper.class);
            return routeMapper.getById(id);
        }
    }

    @Override
    public List<Route> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteMapper routeMapper = session.getMapper(RouteMapper.class);
            return routeMapper.findAll();
        }
    }

    @Override
    public void update(Route entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteMapper routeMapper = session.getMapper(RouteMapper.class);
            routeMapper.update(entity);
            session.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteMapper routeMapper = session.getMapper(RouteMapper.class);
            routeMapper.deleteById(id);
            session.commit();
        }
    }

    @Override
    public List<Route> getRoutesByCityId(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RouteMapper routeMapper = session.getMapper(RouteMapper.class);
            return routeMapper.getRoutesByCityId(id);
        }
    }

}
