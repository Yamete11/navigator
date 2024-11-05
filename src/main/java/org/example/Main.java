package org.example;

import org.example.dao.implementation.RouteMapperImpl;
import org.example.model.City;
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

        cityService.findAll().forEach(System.out::println);
        cityConnectionService.findAll().forEach(System.out::println);
        System.out.println(cityService.getById(1L));
        System.out.println(cityConnectionService.getById(1L));
    }
}