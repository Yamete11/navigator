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


        //Main.createCity();


/*      cityService.findAll().forEach(System.out::println);
        cityConnectionService.findAll().forEach(System.out::println);
        routeService.findAll().forEach(System.out::println);
        routeCityService.findAll().forEach(System.out::println);
        startLocationService.findAll().forEach(System.out::println);
        endLocationService.findAll().forEach(System.out::println);*/



    }

    public static void createCity(){
        RouteCityServiceImpl routeCityService = new RouteCityServiceImpl();
        RouteServiceImpl routeService = new RouteServiceImpl();
        StartLocationServiceImpl startLocationService = new StartLocationServiceImpl();
        EndLocationServiceImpl endLocationService = new EndLocationServiceImpl();
        CityServiceImpl cityService = new CityServiceImpl();
        CityConnectionServiceImpl cityConnectionService = new CityConnectionServiceImpl();

        City city1 = new City();
        city1.setTitle("CityA");
        city1.setX(10.0);
        city1.setY(5.0);
        City city2 = new City();
        city2.setTitle("CityB");
        city2.setX(95.0);
        city2.setY(2.0);
        City city3 = new City();
        city3.setTitle("CityC");
        city3.setX(20.0);
        city3.setY(15.0);
        City city4 = new City();
        city4.setTitle("CityD");
        city4.setX(75.0);
        city4.setY(10.0);
        City city5 = new City();
        city5.setTitle("CityE");
        city5.setX(50.0);
        city5.setY(25.0);
        City city6 = new City();
        city6.setTitle("CityF");
        city6.setX(90.0);
        city6.setY(18.0);
        City city7 = new City();
        city7.setTitle("CityG");
        city7.setX(5.0);
        city7.setY(28.0);

        cityService.create(city1);
        cityService.create(city2);
        cityService.create(city3);
        cityService.create(city4);
        cityService.create(city5);
        cityService.create(city6);
        cityService.create(city7);


        CityConnection cityConnection1 = new CityConnection();
        cityConnection1.setFirstCity(city1);
        cityConnection1.setSecondCity(city7);
        cityConnection1.setDistance(40.0);
        CityConnection cityConnection2 = new CityConnection();
        cityConnection2.setFirstCity(city1);
        cityConnection2.setSecondCity(city3);
        cityConnection2.setDistance(40.0);
        CityConnection cityConnection3 = new CityConnection();
        cityConnection3.setFirstCity(city7);
        cityConnection3.setSecondCity(city5);
        cityConnection3.setDistance(40.0);
        CityConnection cityConnection4 = new CityConnection();
        cityConnection4.setFirstCity(city3);
        cityConnection4.setSecondCity(city5);
        cityConnection4.setDistance(40.0);
        CityConnection cityConnection5 = new CityConnection();
        cityConnection5.setFirstCity(city3);
        cityConnection5.setSecondCity(city4);
        cityConnection5.setDistance(40.0);
        CityConnection cityConnection6 = new CityConnection();
        cityConnection6.setFirstCity(city5);
        cityConnection6.setSecondCity(city6);
        cityConnection6.setDistance(40.0);
        CityConnection cityConnection7 = new CityConnection();
        cityConnection7.setFirstCity(city4);
        cityConnection7.setSecondCity(city6);
        cityConnection7.setDistance(40.0);
        CityConnection cityConnection8 = new CityConnection();
        cityConnection8.setFirstCity(city4);
        cityConnection8.setSecondCity(city2);
        cityConnection8.setDistance(40.0);
        CityConnection cityConnection9 = new CityConnection();
        cityConnection9.setFirstCity(city2);
        cityConnection9.setSecondCity(city6);
        cityConnection9.setDistance(40.0);

        cityConnectionService.create(cityConnection1);
        cityConnectionService.create(cityConnection2);
        cityConnectionService.create(cityConnection3);
        cityConnectionService.create(cityConnection4);
        cityConnectionService.create(cityConnection5);
        cityConnectionService.create(cityConnection6);
        cityConnectionService.create(cityConnection7);
        cityConnectionService.create(cityConnection8);
        cityConnectionService.create(cityConnection9);

        StartLocation startLocation1 = new StartLocation();
        startLocation1.setCity(city1);
        StartLocation startLocation2 = new StartLocation();
        startLocation2.setCity(city7);
        StartLocation startLocation3 = new StartLocation();
        startLocation3.setCity(city2);

        EndLocation endLocation1 = new EndLocation();
        endLocation1.setCity(city5);
        EndLocation endLocation2 = new EndLocation();
        endLocation2.setCity(city6);
        EndLocation endLocation3 = new EndLocation();
        endLocation3.setCity(city3);

        Route route1 = new Route();
        route1.setStartLocation(startLocation1);
        route1.setEndLocation(endLocation1);
        route1.setTotalDistance(50.0);
        Route route2 = new Route();
        route2.setStartLocation(startLocation2);
        route2.setEndLocation(endLocation2);
        route2.setTotalDistance(70.0);
        Route route3 = new Route();
        route3.setStartLocation(startLocation3);
        route3.setEndLocation(endLocation3);
        route3.setTotalDistance(90.0);

        routeService.create(route1);
        routeService.create(route2);
        routeService.create(route3);

        RouteCity routeCity1 = new RouteCity();
        routeCity1.setRoute(route1);
        routeCity1.setCity(city1);
        routeCity1.setOrderIndex(1L);
        RouteCity routeCity2 = new RouteCity();
        routeCity2.setRoute(route1);
        routeCity2.setCity(city3);
        routeCity2.setOrderIndex(2L);
        RouteCity routeCity3 = new RouteCity();
        routeCity3.setRoute(route1);
        routeCity3.setCity(city5);
        routeCity3.setOrderIndex(3L);


        RouteCity routeCity4 = new RouteCity();
        routeCity4.setRoute(route2);
        routeCity4.setCity(city7);
        routeCity4.setOrderIndex(1L);
        RouteCity routeCity5 = new RouteCity();
        routeCity5.setRoute(route2);
        routeCity5.setCity(city5);
        routeCity5.setOrderIndex(2L);
        RouteCity routeCity6 = new RouteCity();
        routeCity6.setRoute(route2);
        routeCity6.setCity(city6);
        routeCity6.setOrderIndex(3L);

        RouteCity routeCity7 = new RouteCity();
        routeCity7.setRoute(route3);
        routeCity7.setCity(city2);
        routeCity7.setOrderIndex(1L);
        RouteCity routeCity8 = new RouteCity();
        routeCity8.setRoute(route3);
        routeCity8.setCity(city4);
        routeCity8.setOrderIndex(2L);
        RouteCity routeCity9 = new RouteCity();
        routeCity9.setRoute(route3);
        routeCity9.setCity(city3);
        routeCity9.setOrderIndex(3L);

        routeCityService.create(routeCity1);
        routeCityService.create(routeCity2);
        routeCityService.create(routeCity3);
        routeCityService.create(routeCity4);
        routeCityService.create(routeCity5);
        routeCityService.create(routeCity6);
        routeCityService.create(routeCity7);
        routeCityService.create(routeCity8);
        routeCityService.create(routeCity9);
    }
}