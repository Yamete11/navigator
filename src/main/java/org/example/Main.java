package org.example;

import org.example.dao.implementation.RouteMapperImpl;
import org.example.model.City;
import org.example.model.CityConnection;
import org.example.service.implementation.CityConnectionServiceImpl;
import org.example.service.implementation.CityServiceImpl;
import org.example.service.implementation.RouteCityServiceImpl;
import org.example.service.implementation.RouteServiceImpl;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        CityServiceImpl cityService = new CityServiceImpl();
        CityConnectionServiceImpl cityConnectionService = new CityConnectionServiceImpl();
        RouteCityServiceImpl routeCityService = new RouteCityServiceImpl();
        RouteServiceImpl routeService = new RouteServiceImpl();


        // CREATING CONNECTION
        /*City city1 = cityService.getById(5L).get();
        City city2 = cityService.getById(1L).get();
        System.out.println(city1);
        System.out.println(city2);

        CityConnection connection = new CityConnection();
        connection.setDistance(45.9);
        connection.setFirstCity(city1);
        connection.setSecondCity(city2);
        cityConnectionService.create(connection);*/


        //cityService.findAll().forEach(System.out::println);
        /*cityConnectionService.getCityConnectionsByCityId(4L).forEach(System.out::println);
        cityConnectionService.findAll().forEach(System.out::println);*/

        /*City city = new City();
        city.setTitle("Hello there");
        city.setX(10.0);
        city.setY(10.0);

        cityService.create(city);*/

        System.out.println(cityService.getById(8L).get());
        City city = cityService.getById(8L).get();
        city.setY(20.0);
        cityService.update(city);
        System.out.println(cityService.getById(8L).get());

    }
}