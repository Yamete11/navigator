package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.model.City;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CityMapper extends GenericDao<City>{

    @Override
    @Insert("INSERT INTO cities (city_id, title, x, y) VALUES (#{id}, #{title}, #{x}, #{y})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    City create(City entity);

    @Override
    @Select("SELECT c.city_id, c.title, c.x, c.y FROM cities c WHERE c.city_id = #{id}")
    @Results({
            @Result(property = "id", column = "city_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "x", column = "x"),
            @Result(property = "y", column = "y")
    })
    Optional<City> getById(Long id);

    @Override
    @Select("SELECT c.city_id, c.title, c.x, c.y FROM cities c")
    @Results({
            @Result(property = "id", column = "city_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "x", column = "x"),
            @Result(property = "y", column = "y")
    })
    List<City> findAll();

    @Override
    @Update("UPDATE cities SET title = #{title}, x = #{x}, y = #{y} WHERE city_id = #{id}")
    void update(City entity);

    @Override
    @Delete("DELETE FROM cities WHERE city_id = #{id}")
    void deleteById(Long id);

    @Select("SELECT c.city_id, c.title, c.x, c.y FROM cities c WHERE c.title = #{title}")
    @Results({
            @Result(property = "id", column = "city_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "x", column = "x"),
            @Result(property = "y", column = "y")
    })
    Optional<City> getByTitle(String title);
}
