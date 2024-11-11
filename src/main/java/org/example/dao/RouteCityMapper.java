package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.model.City;
import org.example.model.RouteCity;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RouteCityMapper extends GenericDao<RouteCity> {

    @Override
    @Insert("INSERT INTO route_cities (city_id, route_id, order_index) VALUES (#{city.id}, #{route.id}, #{orderIndex})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(RouteCity entity);

    @Insert({
            "<script>",
            "INSERT INTO route_cities (city_id, route_id, order_index) VALUES ",
            "<foreach collection='routeCities' item='routeCity' separator=','>",
            "(#{routeCity.city.id}, #{routeCity.route.id}, #{routeCity.orderIndex})",
            "</foreach>",
            "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createBatch(@Param("routeCities") List<RouteCity> routeCities);

    @Override
    @Select("SELECT rc.route_cities_id, " +
            "c.city_id AS city_id, c.title AS city_title, c.x AS city_x, c.y AS city_y, " +
            "r.route_id AS route_id, r.total_distance AS route_total_distance, " +
            "rc.order_index AS order_index " +
            "FROM route_cities rc " +
            "JOIN cities c ON rc.city_id = c.city_id " +
            "JOIN routes r ON rc.route_id = r.route_id " +
            "WHERE rc.route_cities_id = #{id}")
    @Results({
            @Result(column = "route_cities_id", property = "id"),
            @Result(property = "city.id", column = "city_id"),
            @Result(property = "city.title", column = "city_title"),
            @Result(property = "city.x", column = "city_x"),
            @Result(property = "city.y", column = "city_y"),
            @Result(property = "route.id", column = "route_id"),
            @Result(property = "route.totalDistance", column = "route_total_distance"),
            @Result(property = "orderIndex", column = "order_index")
    })
    Optional<RouteCity> getById(@Param("id") Long id);

    @Override
    @Select("SELECT rc.route_cities_id, " +
            "c.city_id AS city_id, c.title AS city_title, c.x AS city_x, c.y AS city_y, " +
            "r.route_id AS route_id, r.total_distance AS route_total_distance, " +
            "rc.order_index AS order_index " +
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
            @Result(property = "route.totalDistance", column = "route_total_distance"),
            @Result(property = "orderIndex", column = "order_index")
    })
    List<RouteCity> findAll();

    @Override
    @Update("UPDATE route_cities SET city_id = #{city.id}, route_id = #{route.id}, order_index = #{orderIndex} WHERE route_cities_id = #{routeCitiesId}")
    void update(RouteCity entity);

    @Override
    @Delete("DELETE FROM route_cities WHERE route_cities_id = #{id}")
    void deleteById(@Param("id") Long id);

    @Delete("DELETE FROM route_cities WHERE city_id = #{id}")
    void deleteByCityId(@Param("id") Long id);

    @Delete("DELETE FROM route_cities WHERE route_id = #{id}")
    void deleteByRouteId(@Param("id") Long id);

    @Select("SELECT c.city_id AS city_id, c.title AS city_title, c.x AS city_x, c.y AS city_y " +
            "FROM route_cities rc " +
            "JOIN cities c ON rc.city_id = c.city_id " +
            "WHERE rc.route_id = #{id} " +
            "ORDER BY rc.order_index")
    @Results({
            @Result(property = "id", column = "city_id"),
            @Result(property = "title", column = "city_title"),
            @Result(property = "x", column = "city_x"),
            @Result(property = "y", column = "city_y")
    })
    List<City> getCitiesByRouteId(@Param("id") Long id);

}
