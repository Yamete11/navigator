package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.model.CityConnection;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CityConnectionMapper extends GenericDao<CityConnection> {

    @Override
    @Insert("INSERT INTO navigator.City_connections (distance, first_city_id, second_city_id) " +
            "VALUES (#{distance}, #{firstCity.id}, #{secondCity.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(CityConnection entity);



    @Override
    @Select("SELECT cc.city_connection_id, cc.distance, " +
            "c1.city_id AS first_city_id, c1.title AS first_city_title, c1.x AS first_city_x, c1.y AS first_city_y, " +
            "c2.city_id AS second_city_id, c2.title AS second_city_title, c2.x AS second_city_x, c2.y AS second_city_y " +
            "FROM navigator.City_connections cc " +
            "JOIN navigator.Cities c1 ON cc.first_city_id = c1.city_id " +
            "JOIN navigator.Cities c2 ON cc.second_city_Id = c2.city_id " +
            "WHERE cc.city_connection_id = #{id}")
    @Results({
            @Result(property = "id", column = "city_connection_id"),
            @Result(property = "distance", column = "distance"),
            @Result(property = "firstCity.id", column = "first_city_id"),
            @Result(property = "firstCity.title", column = "first_city_title"),
            @Result(property = "firstCity.x", column = "first_city_x"),
            @Result(property = "firstCity.y", column = "first_city_y"),
            @Result(property = "secondCity.id", column = "second_city_id"),
            @Result(property = "secondCity.title", column = "second_city_title"),
            @Result(property = "secondCity.x", column = "second_city_x"),
            @Result(property = "secondCity.y", column = "second_city_y")
    })
    Optional<CityConnection> getById(Long id);

    @Override
    @Select("SELECT cc.city_connection_id, cc.distance, " +
            "c1.city_id AS first_city_id, c1.title AS first_city_title, c1.x AS first_city_x, c1.y AS first_city_y, " +
            "c2.city_id AS second_city_id, c2.title AS second_city_title, c2.x AS second_city_x, c2.y AS second_city_y " +
            "FROM navigator.City_connections cc " +
            "JOIN navigator.Cities c1 ON cc.first_city_id = c1.city_id " +
            "JOIN navigator.Cities c2 ON cc.second_city_Id = c2.city_id")
    @Results({
            @Result(property = "id", column = "city_connection_id"),
            @Result(property = "distance", column = "distance"),
            @Result(property = "firstCity.id", column = "first_city_id"),
            @Result(property = "firstCity.title", column = "first_city_title"),
            @Result(property = "firstCity.x", column = "first_city_x"),
            @Result(property = "firstCity.y", column = "first_city_y"),
            @Result(property = "secondCity.id", column = "second_city_id"),
            @Result(property = "secondCity.title", column = "second_city_title"),
            @Result(property = "secondCity.x", column = "second_city_x"),
            @Result(property = "secondCity.y", column = "second_city_y")
    })
    List<CityConnection> findAll();


    @Override
    @Update("UPDATE navigator.City_connections SET distance = #{distance}, first_city_id = #{firstCity.id}, second_city_Id = #{secondCity.id} " +
            "WHERE city_connection_id = #{id}")
    void update(CityConnection entity);

    @Override
    @Delete("DELETE FROM navigator.City_connections WHERE city_connection_id = #{id}")
    void deleteById(Long id);

    @Delete("DELETE FROM navigator.City_connections WHERE first_city_id = #{id} OR second_city_id = #{id}")
    void deleteByCityId(Long id);


    @Select("SELECT cc.city_connection_id, cc.distance, " +
            "c1.city_id AS first_city_id, c1.title AS first_city_title, c1.x AS first_city_x, c1.y AS first_city_y, " +
            "c2.city_id AS second_city_id, c2.title AS second_city_title, c2.x AS second_city_x, c2.y AS second_city_y " +
            "FROM navigator.City_connections cc " +
            "JOIN navigator.Cities c1 ON cc.first_city_id = c1.city_id " +
            "JOIN navigator.Cities c2 ON cc.second_city_Id = c2.city_id " +
            "WHERE first_city_id = #{id} or second_city_id = #{id}")
    @Results({
            @Result(property = "id", column = "city_connection_id"),
            @Result(property = "distance", column = "distance"),
            @Result(property = "firstCity.id", column = "first_city_id"),
            @Result(property = "firstCity.title", column = "first_city_title"),
            @Result(property = "firstCity.x", column = "first_city_x"),
            @Result(property = "firstCity.y", column = "first_city_y"),
            @Result(property = "secondCity.id", column = "second_city_id"),
            @Result(property = "secondCity.title", column = "second_city_title"),
            @Result(property = "secondCity.x", column = "second_city_x"),
            @Result(property = "secondCity.y", column = "second_city_y")
    })
    List<CityConnection> getCityConnectionsByCityId(Long id);
}

