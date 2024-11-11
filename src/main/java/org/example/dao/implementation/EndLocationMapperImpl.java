package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.config.ConnectionManager;
import org.example.dao.EndLocationMapper;
import org.example.model.EndLocation;

import java.util.List;
import java.util.Optional;

public class EndLocationMapperImpl implements EndLocationMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public EndLocationMapperImpl() {
        this.sqlSessionFactory = ConnectionManager.getInstance().getSessionFactory();
    }

    @Override
    public void create(EndLocation entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EndLocationMapper endLocationMapper = session.getMapper(EndLocationMapper.class);
            endLocationMapper.create(entity);
            session.commit();
        }
    }

    @Override
    public Optional<EndLocation> getById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EndLocationMapper endLocationMapper = session.getMapper(EndLocationMapper.class);
            return endLocationMapper.getById(id);
        }
    }

    @Override
    public List<EndLocation> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EndLocationMapper endLocationMapper = session.getMapper(EndLocationMapper.class);
            return endLocationMapper.findAll();
        }
    }

    @Override
    public void update(EndLocation entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EndLocationMapper endLocationMapper = session.getMapper(EndLocationMapper.class);
            endLocationMapper.update(entity);
            session.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EndLocationMapper endLocationMapper = session.getMapper(EndLocationMapper.class);
            endLocationMapper.deleteById(id);
            session.commit();
        }
    }

    @Override
    public void deleteByCityId(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EndLocationMapper endLocationMapper = session.getMapper(EndLocationMapper.class);
            endLocationMapper.deleteByCityId(id);
            session.commit();
        }
    }
}
