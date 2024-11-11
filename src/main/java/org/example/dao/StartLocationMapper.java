package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.model.StartLocation;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StartLocationMapper extends GenericDao<StartLocation> {

    @Override
    @Insert("INSERT INTO start_locations (start_location_id, city_id) VALUES (#{id}, #{city.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(StartLocation entity);

    @Override
    @Select("SELECT sl.start_location_id, c.city_id, c.title, c.x, c.y " +
            "FROM start_locations sl " +
            "JOIN cities c ON sl.city_id = c.city_id " +
            "WHERE sl.start_location_id = #{id}")
    @Results({
            @Result(property = "id", column = "start_location_id"),
            @Result(property = "city.id", column = "city_id"),
            @Result(property = "city.title", column = "title"),
            @Result(property = "city.x", column = "x"),
            @Result(property = "city.y", column = "y")
    })
    Optional<StartLocation> getById(Long id);

    @Override
    @Select("SELECT sl.start_location_id, c.city_id, c.title, c.x, c.y " +
            "FROM start_locations sl " +
            "JOIN cities c ON sl.city_id = c.city_id")
    @Results({
            @Result(property = "id", column = "start_location_id"),
            @Result(property = "city.id", column = "city_id"),
            @Result(property = "city.title", column = "title"),
            @Result(property = "city.x", column = "x"),
            @Result(property = "city.y", column = "y")
    })
    List<StartLocation> findAll();

    @Override
    @Update("UPDATE start_locations SET city_id = #{city.id} WHERE start_location_id = #{id}")
    void update(StartLocation entity);

    @Override
    @Delete("DELETE FROM start_locations WHERE start_location_id = #{id}")
    void deleteById(Long id);

    @Delete("DELETE FROM start_locations WHERE city_id = #{id}")
    void deleteByCityId(Long id);
}
