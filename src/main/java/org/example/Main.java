package org.example;

import org.example.service.implementation.*;
import org.example.service.observer.CityLogger;
import org.example.service.observer.RouteLogger;

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

        CityServiceImpl cityService = new CityServiceImpl();
        CityConnectionServiceImpl cityConnectionService = new CityConnectionServiceImpl();
        RouteServiceImpl routeService = new RouteServiceImpl();

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

        NavigatorInteraction navigatorInteraction = new NavigatorInteraction(cityService, routeService);
        navigatorInteraction.start();
    }
}
