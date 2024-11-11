package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.config.ConnectionManager;
import org.example.dao.CityConnectionMapper;
import org.example.model.CityConnection;

import java.util.List;
import java.util.Optional;

public class CityConnectionMapperImpl implements CityConnectionMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public CityConnectionMapperImpl() {
        this.sqlSessionFactory = ConnectionManager.getInstance().getSessionFactory();
    }

    @Override
    public void create(CityConnection entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityConnectionMapper cityConnectionMapper = session.getMapper(CityConnectionMapper.class);
            cityConnectionMapper.create(entity);
            session.commit();
        }
    }

    @Override
    public Optional<CityConnection> getById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityConnectionMapper cityConnectionMapper = session.getMapper(CityConnectionMapper.class);
            return cityConnectionMapper.getById(id);
        }
    }

    @Override
    public List<CityConnection> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityConnectionMapper cityConnectionMapper = session.getMapper(CityConnectionMapper.class);
            return cityConnectionMapper.findAll();
        }
    }

    @Override
    public void update(CityConnection entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityConnectionMapper cityConnectionMapper = session.getMapper(CityConnectionMapper.class);
            cityConnectionMapper.update(entity);
            session.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityConnectionMapper cityConnectionMapper = session.getMapper(CityConnectionMapper.class);
            cityConnectionMapper.deleteById(id);
            session.commit();
        }
    }

    @Override
    public void deleteByCityId(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityConnectionMapper cityConnectionMapper = session.getMapper(CityConnectionMapper.class);
            cityConnectionMapper.deleteByCityId(id);
            session.commit();
        }
    }

    @Override
    public List<CityConnection> getCityConnectionsByCityId(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityConnectionMapper cityConnectionMapper = session.getMapper(CityConnectionMapper.class);
            return cityConnectionMapper.getCityConnectionsByCityId(id);
        }
    }
}
