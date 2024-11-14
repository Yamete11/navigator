package org.example.dao;

import org.apache.ibatis.annotations.*;
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
    List<Route> getRoutesByCityId(Long cityId);
}
