package org.example;

import org.example.model.*;
import org.example.service.implementation.*;

public class Main {
    public static void main(String[] args) {
        CityServiceImpl cityService = new CityServiceImpl();
        CityConnectionServiceImpl cityConnectionService = new CityConnectionServiceImpl();
        RouteCityServiceImpl routeCityService = new RouteCityServiceImpl();
        RouteServiceImpl routeService = new RouteServiceImpl();
        StartLocationServiceImpl startLocationService = new StartLocationServiceImpl();
        EndLocationServiceImpl endLocationService = new EndLocationServiceImpl();

        /*StartLocation startLocation = new StartLocation();
        startLocation.setCity(cityService.getById(1L).get());
        startLocationService.create(startLocation);*/

        /*EndLocation endLocation = new EndLocation();
        endLocation.setCity(cityService.getById(2L).get());
        endLocationService.create(endLocation);*/

        /*StartLocation startLocation =  startLocationService.getById(1L).get();
        EndLocation endLocation =  endLocationService.getById(1L).get();

        Route route = new Route();
        route.setTotalDistance(122.0);
        route.setStartLocation(startLocation);
        route.setEndLocation(endLocation);

        routeService.create(route);
*/

        /*StartLocation startLocation = new StartLocation();
        EndLocation endLocation = new EndLocation();

        startLocation.setCity(cityService.getById(7L).get());
        endLocation.setCity(cityService.getById(8L).get());

        Route route = new Route();
        route.setTotalDistance(137.0);
        route.setStartLocation(startLocation);
        route.setEndLocation(endLocation);

        routeService.create(route);
        routeService.findAll().forEach(System.out::println);*/

        System.out.println(routeService.getById(19L));

        routeCityService.getCitiesByRouteId(19L).forEach(System.out::println);

        routeService.deleteById(19L);



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

        /*System.out.println(cityService.getById(8L).get());
        City city = cityService.getById(8L).get();
        city.setY(20.0);
        cityService.update(city);
        System.out.println(cityService.getById(8L).get());*/

    }
}