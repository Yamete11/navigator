package org.example;

import org.example.dao.CityMapper;
import org.example.dao.implementation.CityConnectionMapperImpl;
import org.example.dao.implementation.CityMapperImpl;
import org.example.dao.implementation.RouteMapperImpl;
import org.example.service.CityConnectionService;
import org.example.service.CityService;
import org.example.service.RouteService;
import org.example.service.implementation.*;
import org.example.service.observer.CityLogger;
import org.example.service.observer.RouteLogger;
import org.example.ui.GraphDrawer;
import org.example.utils.implementation.CityNavigator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Navigator application!");
        System.out.println("Choose initial data source:");
        System.out.println("1 - Use static predefined data");
        System.out.println("2 - Generate random data");
        System.out.println("3 - Start without initial data");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        CityService cityService = new CityServiceImpl(new CityMapperImpl(), new CityConnectionServiceImpl(new CityConnectionMapperImpl()),new RouteCityServiceImpl());
        CityConnectionService cityConnectionService = new CityConnectionServiceImpl(new CityConnectionMapperImpl());
        RouteService routeService = new RouteServiceImpl(
                new RouteMapperImpl(),
                new StartLocationServiceImpl(),
                new EndLocationServiceImpl(),
                new RouteCityServiceImpl(),
                new CityNavigator());

        CityLogger cityLogger = new CityLogger();
        RouteLogger routeLogger = new RouteLogger();
        cityService.addObserver(cityLogger);

        routeService.addObserver(routeLogger);

        switch (choice) {
            case 1 -> {
                CityGenerator.createCity(cityService, cityConnectionService);
                System.out.println("Static data has been loaded");
            }
            case 2 -> {
                CityGenerator.generateRandomCities(cityService, cityConnectionService);
                System.out.println("Random data has been generated");
            }
            case 3 -> System.out.println("Starting without any initial data");
            default -> {
                System.out.println("Invalid choice. Exiting the application");
                return;
            }
        }

        NavigatorInteraction navigatorInteraction = new NavigatorInteraction(cityService, routeService,new GraphDrawer(cityConnectionService),cityConnectionService);
        navigatorInteraction.start();
    }
}
