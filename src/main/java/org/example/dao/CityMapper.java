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
    void create(City entity);

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


    @Select("SELECT c.city_id, c.title, c.x, c.y FROM cities c WHERE c.x = #{x} AND c.y = #{y}")
    @Results({
            @Result(property = "id", column = "city_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "x", column = "x"),
            @Result(property = "y", column = "y")
    })
    Optional<City> getByCoordinates(@Param("x") double x, @Param("y") double y);

    @Select("SELECT c.city_id AS id, c.title, c.x, c.y " +
            "FROM cities c " +
            "WHERE c.city_id NOT IN (" +
            "    SELECT DISTINCT cc.first_city_id FROM city_connections cc " +
            "    UNION " +
            "    SELECT DISTINCT cc.second_city_id FROM city_connections cc" +
            ")")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "x", property = "x"),
            @Result(column = "y", property = "y")
    })
    List<City> getCitiesNotInConnections();

}
