package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.model.CityConnection;
import org.example.model.Route;
import org.example.model.StartLocation;
import org.example.model.EndLocation;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RouteMapper extends GenericDao<Route> {

    @Override
    @Insert("INSERT INTO Routes (total_distance, start_location_id, end_location_id) " +
            "VALUES (#{totalDistance}, #{startLocation.id}, #{endLocation.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Route entity);

    @Override
    @Select("SELECT r.route_id AS id, r.total_distance, " +
            "sl.start_location_id AS start_location_id, sl.city_id AS start_city_id, c1.title AS start_city_title, c1.x AS start_x, c1.y AS start_y, " +
            "el.end_location_id AS end_location_id, el.city_id AS end_city_id, c2.title AS end_city_title, c2.x AS end_x, c2.y AS end_y " +
            "FROM Routes r " +
            "JOIN start_locations sl ON r.start_location_id = sl.start_location_id " +
            "JOIN cities c1 ON sl.city_id = c1.city_id " +
            "JOIN end_locations el ON r.end_location_id = el.end_location_id " +
            "JOIN cities c2 ON el.city_id = c2.city_id " +
            "WHERE r.route_id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "total_distance", property = "totalDistance"),
            @Result(property = "startLocation.id", column = "start_location_id"),
            @Result(property = "startLocation.city.id", column = "start_city_id"),
            @Result(property = "startLocation.city.title", column = "start_city_title"),
            @Result(property = "startLocation.city.x", column = "start_x"),
            @Result(property = "startLocation.city.y", column = "start_y"),
            @Result(property = "endLocation.id", column = "end_location_id"),
            @Result(property = "endLocation.city.id", column = "end_city_id"),
            @Result(property = "endLocation.city.title", column = "end_city_title"),
            @Result(property = "endLocation.city.x", column = "end_x"),
            @Result(property = "endLocation.city.y", column = "end_y")
    })
    Optional<Route> getById(Long id);

    @Override
    @Select("SELECT r.route_id AS id, r.total_distance, " +
            "sl.start_location_id AS start_location_id, sl.city_id AS start_city_id, c1.title AS start_city_title, c1.x AS start_x, c1.y AS start_y, " +
            "el.end_location_id AS end_location_id, el.city_id AS end_city_id, c2.title AS end_city_title, c2.x AS end_x, c2.y AS end_y " +
            "FROM Routes r " +
            "JOIN start_locations sl ON r.start_location_id = sl.start_location_id " +
            "JOIN cities c1 ON sl.city_id = c1.city_id " +
            "JOIN end_locations el ON r.end_location_id = el.end_location_id " +
            "JOIN cities c2 ON el.city_id = c2.city_id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "total_distance", property = "totalDistance"),
            @Result(property = "startLocation.id", column = "start_location_id"),
            @Result(property = "startLocation.city.id", column = "start_city_id"),
            @Result(property = "startLocation.city.title", column = "start_city_title"),
            @Result(property = "startLocation.city.x", column = "start_x"),
            @Result(property = "startLocation.city.y", column = "start_y"),
            @Result(property = "endLocation.id", column = "end_location_id"),
            @Result(property = "endLocation.city.id", column = "end_city_id"),
            @Result(property = "endLocation.city.title", column = "end_city_title"),
            @Result(property = "endLocation.city.x", column = "end_x"),
            @Result(property = "endLocation.city.y", column = "end_y")
    })
    List<Route> findAll();

    @Override
    @Update("UPDATE Routes SET total_distance = #{totalDistance}, " +
            "start_location_id = #{startLocation.id}, end_location_id = #{endLocation.id} " +
            "WHERE route_id = #{id}")
    void update(Route entity);

    @Override
    @Delete("DELETE FROM Routes WHERE route_id = #{id}")
    void deleteById(Long id);

    @Select("SELECT r.route_id AS id, r.total_distance, " +
            "sl.start_location_id AS start_location_id, sl.city_id AS start_city_id, c1.title AS start_city_title, c1.x AS start_x, c1.y AS start_y, " +
            "el.end_location_id AS end_location_id, el.city_id AS end_city_id, c2.title AS end_city_title, c2.x AS end_x, c2.y AS end_y " +
            "FROM Routes r " +
            "JOIN start_locations sl ON r.start_location_id = sl.start_location_id " +
            "JOIN cities c1 ON sl.city_id = c1.city_id " +
            "JOIN end_locations el ON r.end_location_id = el.end_location_id " +
            "JOIN cities c2 ON el.city_id = c2.city_id " +
            "WHERE sl.city_id = #{cityId} OR el.city_id = #{cityId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "total_distance", property = "totalDistance"),
            @Result(property = "startLocation.id", column = "start_location_id"),
            @Result(property = "startLocation.city.id", column = "start_city_id"),
            @Result(property = "startLocation.city.title", column = "start_city_title"),
            @Result(property = "startLocation.city.x", column = "start_x"),
            @Result(property = "startLocation.city.y", column = "start_y"),
            @Result(property = "endLocation.id", column = "end_location_id"),
            @Result(property = "endLocation.city.id", column = "end_city_id"),
            @Result(property = "endLocation.city.title", column = "end_city_title"),
            @Result(property = "endLocation.city.x", column = "end_x"),
            @Result(property = "endLocation.city.y", column = "end_y")
    })
    List<Route> getRoutesByCityId(@Param("cityId") Long cityId);

    @Select("SELECT r.route_id AS id, r.total_distance, " +
            "sl.start_location_id AS start_location_id, sl.city_id AS start_city_id, c1.title AS start_city_title, c1.x AS start_x, c1.y AS start_y, " +
            "el.end_location_id AS end_location_id, el.city_id AS end_city_id, c2.title AS end_city_title, c2.x AS end_x, c2.y AS end_y " +
            "FROM Routes r " +
            "JOIN start_locations sl ON r.start_location_id = sl.start_location_id " +
            "JOIN cities c1 ON sl.city_id = c1.city_id " +
            "JOIN end_locations el ON r.end_location_id = el.end_location_id " +
            "JOIN cities c2 ON el.city_id = c2.city_id " +
            "WHERE (sl.city_id = #{city1Id} AND el.city_id = #{city2Id}) " +
            "OR (sl.city_id = #{city2Id} AND el.city_id = #{city1Id})")
    Route checkIfRouteExists(@Param("city1Id") Long city1Id, @Param("city2Id") Long city2Id);

    @Select("SELECT cc.city_connection_id AS city_connection_id, " +
            "cc.first_city_id AS first_city_id, c1.title AS first_city_title, c1.x AS first_city_x, c1.y AS first_city_y, " +
            "cc.second_city_id AS second_city_id, c2.title AS second_city_title, c2.x AS second_city_x, c2.y AS second_city_y, " +
            "cc.distance AS distance " +
            "FROM city_connections cc " +
            "JOIN cities c1 ON cc.first_city_id = c1.city_id " +
            "JOIN cities c2 ON cc.second_city_id = c2.city_id " +
            "JOIN (" +
            "    SELECT rc1.city_id AS first_city_id, rc2.city_id AS second_city_id " +
            "    FROM route_cities rc1 " +
            "    JOIN route_cities rc2 ON rc1.route_id = rc2.route_id " +
            "    AND rc1.order_index + 1 = rc2.order_index " +
            "    WHERE rc1.route_id = #{routeId} " +
            ") connections " +
            "ON cc.first_city_id = connections.first_city_id " +
            "AND cc.second_city_id = connections.second_city_id")
    @Results({
            @Result(column = "city_connection_id", property = "id"),
            @Result(column = "first_city_id", property = "firstCity.id"),
            @Result(column = "first_city_title", property = "firstCity.title"),
            @Result(column = "first_city_x", property = "firstCity.x"),
            @Result(column = "first_city_y", property = "firstCity.y"),
            @Result(column = "second_city_id", property = "secondCity.id"),
            @Result(column = "second_city_title", property = "secondCity.title"),
            @Result(column = "second_city_x", property = "secondCity.x"),
            @Result(column = "second_city_y", property = "secondCity.y"),
            @Result(column = "distance", property = "distance")
    })
    List<CityConnection> getCityConnectionsByRouteId(@Param("routeId") Long routeId);






}
