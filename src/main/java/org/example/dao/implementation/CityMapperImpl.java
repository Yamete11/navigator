package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.config.ConnectionManager;
import org.example.dao.CityMapper;
import org.example.model.City;

import java.util.List;
import java.util.Optional;

public class CityMapperImpl implements CityMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public CityMapperImpl() {
        this.sqlSessionFactory = ConnectionManager.getInstance().getSessionFactory();
    }

    @Override
    public City create(City entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            cityMapper.create(entity);
            session.commit();
            return entity;
        }
    }

    @Override
    public Optional<City> getById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            return cityMapper.getById(id);
        }
    }

    @Override
    public List<City> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            return cityMapper.findAll();
        }
    }

    @Override
    public void update(City entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            cityMapper.update(entity);
            session.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            cityMapper.deleteById(id);
            session.commit();
        }
    }

    @Override
    public Optional<City> getByTitle(String title) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            return cityMapper.getByTitle(title);
        }
    }

}
