package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.model.RouteCity;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RouteCityMapper extends GenericDao<RouteCity> {

    @Override
    @Insert("INSERT INTO route_cities (city_id, route_id) VALUES (#{city.id}, #{route.id})")
    @Options(useGeneratedKeys = true, keyProperty = "routeCitiesId")
    RouteCity create(RouteCity entity);

    @Override
    @Select("SELECT rc.route_cities_id, " +
            "c.city_id AS city_id, c.title AS city_title, c.x AS city_x, c.y AS city_y, " +
            "r.route_id AS route_id, r.total_distance AS route_total_distance " +
            "FROM route_cities rc " +
            "JOIN cities c ON rc.city_id = c.city_id " +
            "JOIN routes r ON rc.route_id = r.route_id " +
            "WHERE rc.route_cities_id = #{id}")
    @Results({
            @Result(column = "route_cities_id", property = "routeCitiesId"),
            @Result(property = "city.id", column = "city_id"),
            @Result(property = "city.title", column = "city_title"),
            @Result(property = "city.x", column = "city_x"),
            @Result(property = "city.y", column = "city_y"),
            @Result(property = "route.id", column = "route_id"),
            @Result(property = "route.totalDistance", column = "route_total_distance")
    })
    Optional<RouteCity> getById(Long id);

    @Override
    @Select("SELECT rc.route_cities_id, " +
            "c.city_id AS city_id, c.title AS city_title, c.x AS city_x, c.y AS city_y, " +
            "r.route_id AS route_id, r.total_distance AS route_total_distance " +
            "FROM route_cities rc " +
            "JOIN cities c ON rc.city_id = c.city_id " +
            "JOIN routes r ON rc.route_id = r.route_id")
    @Results({
            @Result(column = "route_cities_id", property = "routeCitiesId"),
            @Result(property = "city.id", column = "city_id"),
            @Result(property = "city.title", column = "city_title"),
            @Result(property = "city.x", column = "city_x"),
            @Result(property = "city.y", column = "city_y"),
            @Result(property = "route.id", column = "route_id"),
            @Result(property = "route.totalDistance", column = "route_total_distance")
    })
    List<RouteCity> findAll();

    @Override
    @Update("UPDATE route_cities SET city_id = #{city.id}, route_id = #{route.id} WHERE route_cities_id = #{routeCitiesId}")
    void update(RouteCity entity);

    @Override
    @Delete("DELETE FROM route_cities WHERE route_cities_id = #{id}")
    void deleteById(Long id);
}
