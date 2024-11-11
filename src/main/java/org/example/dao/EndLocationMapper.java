package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.model.EndLocation;
import org.example.model.City;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EndLocationMapper extends GenericDao<EndLocation> {

    @Override
    @Insert("INSERT INTO end_locations (end_location_id, city_id) VALUES (#{id}, #{city.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(EndLocation entity);

    @Override
    @Select("SELECT el.end_location_id, c.city_id, c.title, c.x, c.y " +
            "FROM end_locations el " +
            "JOIN cities c ON el.city_id = c.city_id " +
            "WHERE el.end_location_id = #{id}")
    @Results({
            @Result(property = "id", column = "end_location_id"),
            @Result(property = "city.id", column = "city_id"),
            @Result(property = "city.title", column = "title"),
            @Result(property = "city.x", column = "x"),
            @Result(property = "city.y", column = "y")
    })
    Optional<EndLocation> getById(Long id);

    @Override
    @Select("SELECT el.end_location_id, c.city_id, c.title, c.x, c.y " +
            "FROM end_locations el " +
            "JOIN cities c ON el.city_id = c.city_id")
    @Results({
            @Result(property = "id", column = "end_location_id"),
            @Result(property = "city.id", column = "city_id"),
            @Result(property = "city.title", column = "title"),
            @Result(property = "city.x", column = "x"),
            @Result(property = "city.y", column = "y")
    })
    List<EndLocation> findAll();

    @Override
    @Update("UPDATE end_locations SET city_id = #{city.id} WHERE end_location_id = #{id}")
    void update(EndLocation entity);

    @Override
    @Delete("DELETE FROM end_locations WHERE end_location_id = #{id}")
    void deleteById(Long id);

    @Delete("DELETE FROM end_locations WHERE city_id = #{id}")
    void deleteByCityId(Long id);
}
