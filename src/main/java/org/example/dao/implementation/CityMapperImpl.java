package org.example.dao.implementation;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.config.ConnectionManager;
import org.example.dao.CityMapper;
import org.example.model.City;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class CityMapperImpl implements CityMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public CityMapperImpl() {
        this.sqlSessionFactory = ConnectionManager.getInstance().getSessionFactory();
    }

    @Override
    public void create(City entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);

            Optional<City> existingCity = cityMapper.getByCoordinates(entity.getX(), entity.getY());
            if (existingCity.isPresent()) {
                throw new IllegalArgumentException(
                        String.format("City with coordinates (%.2f, %.2f) already exists.", entity.getX(), entity.getY()));
            }

            cityMapper.create(entity);
            session.commit();
        } catch (PersistenceException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new IllegalArgumentException("City with the same name already exists.", e);
            }
            throw new RuntimeException("Error occurred while creating the city: " + e.getMessage(), e);
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

            Optional<City> existingCity = cityMapper.getByCoordinates(entity.getX(), entity.getY());
            if (existingCity.isPresent() && !existingCity.get().getId().equals(entity.getId())) {
                throw new IllegalArgumentException(
                        String.format("City with coordinates (%.2f, %.2f) already exists.", entity.getX(), entity.getY()));
            }

            cityMapper.update(entity);
            session.commit();
        } catch (PersistenceException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new IllegalArgumentException("City with the same name already exists.", e);
            }
            throw new RuntimeException("Error occurred while updating the city: " + e.getMessage(), e);
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

    @Override
    public Optional<City> getByCoordinates(double x, double y) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            return cityMapper.getByCoordinates(x, y);
        }
    }

}
