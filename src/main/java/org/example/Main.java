package org.example;

import org.example.model.*;
import org.example.service.CityConnectionService;
import org.example.service.implementation.*;
import org.example.service.observer.CityLogger;
import org.example.ui.GraphDrawer;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static boolean isRunning = true;
    public static void main(String[] args) {
        CityServiceImpl cityService = new CityServiceImpl();
        CityConnectionServiceImpl cityConnectionService = new CityConnectionServiceImpl();
        RouteCityServiceImpl routeCityService = new RouteCityServiceImpl();
        RouteServiceImpl routeService = new RouteServiceImpl();
        StartLocationServiceImpl startLocationService = new StartLocationServiceImpl();
        EndLocationServiceImpl endLocationService = new EndLocationServiceImpl();

        /*createCity();
        createRoute();*/
        displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim();
            handleCommand(command, cityService, cityConnectionService, routeService);
        }

        scanner.close();



    }

    //- create City - insert city_connection
    //- create Route - provide start and end City
    //- delete city
    //- update city

    private static void handleCommand(String command, CityServiceImpl cityService, CityConnectionServiceImpl cityConnectionService, RouteServiceImpl routeService) {
        switch (command) {
            case "-ac", "--add-city" -> handleAddCity(cityService);
            case "-ar", "--add-route" -> handleAddRoute(routeService, cityService);
            case "-dc", "--delete-city" -> handleDeleteCity(cityService);
            case "-u", "--update-city" -> handleUpdateCity(cityService);
            case "-dm", "--draw-map" -> handleDrawMap(cityConnectionService);
            case "-h", "--help" -> displayHelpMessage();
            case "-q", "--quit" -> {
                isRunning = false;
                System.out.println("Exiting the Navigator application.");
            }
            default -> System.out.println("Unknown command: " + command);
        }
    }

    private static void handleAddCity(CityServiceImpl cityService) {
        CityConnectionServiceImpl cityConnectionService = new CityConnectionServiceImpl();
        Scanner scanner = new Scanner(System.in);

        List<City> cityList = cityService.findAll();

        String title;
        while (true) {
            System.out.print("Enter city name (1 character): ");
            title = scanner.nextLine().trim();
            if (title.length() == 1) {
                break;
            } else {
                System.err.println("Invalid input. City name must be exactly one character.");
            }
        }

        double x = 0.0, y = 0.0;
        try {
            System.out.print("Enter x coordinate (1.0-95.0): ");
            x = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter y coordinate (1.0-95.0): ");
            y = Double.parseDouble(scanner.nextLine().trim());

            if (x < 1.0 || x > 95.0 || y < 1.0 || y > 95.0) {
                throw new IllegalArgumentException("Coordinates are out of bounds. x and y should be between 1.0 and 95.0.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Coordinates must be numbers.");
            return;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        City city = new City();
        city.setTitle(title);
        city.setX(x);
        city.setY(y);

        try {
            cityService.create(city);
            System.out.println("City added successfully: " + city);
        } catch (Exception e) {
            System.err.println("Error adding city: " + e.getMessage());
            return;
        }

        try {
            System.out.println("Choose two cities to connect the new city with:");

            System.out.println("Available cities:");
            for (City c : cityList) {
                if (!c.getTitle().equals(city.getTitle())) {
                    System.out.println(c.getId() + ": " + c.getTitle());
                }
            }

            System.out.print("Enter the ID of the first city to connect: ");
            long firstCityId = Integer.parseInt(scanner.nextLine().trim());
            Optional<City> firstCity = cityService.getById(firstCityId);
            if (firstCity.isEmpty()) {
                System.err.println("City with ID " + firstCityId + " does not exist.");
                return;
            }

            System.out.print("Enter the ID of the second city to connect: ");
            long secondCityId = Integer.parseInt(scanner.nextLine().trim());
            Optional<City> secondCity = cityService.getById(secondCityId);
            if (secondCity.isEmpty()) {
                System.err.println("City with ID " + secondCityId + " does not exist.");
                return;
            }

            CityConnection connection1 = new CityConnection();
            connection1.setFirstCity(city);
            connection1.setSecondCity(firstCity.get());
            connection1.setDistance(0.0d);

            CityConnection connection2 = new CityConnection();
            connection2.setFirstCity(city);
            connection2.setSecondCity(secondCity.get());
            connection2.setDistance(0.0d);


            cityConnectionService.create(connection1);
            cityConnectionService.create(connection2);

            System.out.println("City successfully connected to cities with IDs " + firstCityId + " and " + secondCityId + ".");
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. City IDs must be numbers.");
        } catch (Exception e) {
            System.err.println("Error connecting cities: " + e.getMessage());
        }
    }





    private static void handleAddRoute(RouteServiceImpl routeService, CityServiceImpl cityService) {
        // Implement the logic to add a route
        System.out.println("Route added");
    }

    private static void handleDeleteCity(CityServiceImpl cityService) {
        Scanner scanner = new Scanner(System.in);

        List<City> cities = cityService.findAll();

        if (cities.isEmpty()) {
            System.out.println("No cities available to delete");
            return;
        }

        System.out.println("Available cities:");
        for (City city : cities) {
            System.out.println(city.getId() + ": " + city.getTitle());
        }

        System.out.print("Enter the ID of the city you want to delete: ");
        long cityId;
        try {
            cityId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. City ID must be a number");
            return;
        }

        Optional<City> cityToDelete = cityService.getById(cityId);
        if (cityToDelete.isEmpty()) {
            System.err.println("City with ID " + cityId + " does not exist.");
            return;
        }

        try {
            cityService.deleteById(cityToDelete.get().getId());
            System.out.println("City deleted successfully: " + cityToDelete);
        } catch (Exception e) {
            System.err.println("Error deleting city: " + e.getMessage());
        }
    }


    private static void handleUpdateCity(CityServiceImpl cityService) {
        Scanner scanner = new Scanner(System.in);

        List<City> cities = cityService.findAll();

        if (cities.isEmpty()) {
            System.out.println("No cities available to update.");
            return;
        }

        System.out.println("Available cities:");
        for (City city : cities) {
            System.out.println(city.getId() + ": " + city.getTitle());
        }

        System.out.print("Enter the ID of the city you want to update: ");
        long cityId;
        try {
            cityId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. City ID must be a number.");
            return;
        }

        Optional<City> cityToUpdate = cityService.getById(cityId);
        if (cityToUpdate.isEmpty()) {
            System.err.println("City with ID " + cityId + " does not exist.");
            return;
        }
        City city = cityToUpdate.get();

        String title;
        while (true) {
            System.out.print("Enter new city name (1 character): ");
            title = scanner.nextLine().trim();
            if (title.length() == 1) {
                break;
            } else {
                System.err.println("City name must be exactly one character.");
            }
        }
        city.setTitle(title);

        double x = 0.0, y = 0.0;
        try {
            System.out.print("Enter new x coordinate (1.0-95.0): ");
            x = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter new y coordinate (1.0-95.0): ");
            y = Double.parseDouble(scanner.nextLine().trim());

            if (x < 1.0 || x > 95.0 || y < 1.0 || y > 95.0) {
                throw new IllegalArgumentException("Coordinates are out of bounds. x and y should be between 1.0 and 95.0.");
            }

            city.setX(x);
            city.setY(y);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Coordinates must be numbers.");
            return;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        try {
            cityService.update(city);
            System.out.println("City updated successfully: " + cityToUpdate);
        } catch (Exception e) {
            System.err.println("Error updating city: " + e.getMessage());
        }
    }

    private static void handleDrawMap(CityConnectionService cityConnectionService) {
        System.out.println(new GraphDrawer(cityConnectionService).draw());
    }


    private static void displayWelcomeMessage() {
        System.out.println("========================== Navigator Application ==========================");
        System.out.println("Welcome to the Navigator application! You can manage cities and routes, find optimal paths, and view city maps.");
        displayHelpMessage();
    }
    private static void displayHelpMessage() {
        System.out.println("Available Commands:");
        System.out.println("-ac : --add-city : Add a new city");
        System.out.println("-ar : --add-route : Add a new route");
        System.out.println("-dc : --delete-city : Delete an existing city");
        System.out.println("-u  : --update-city : Update city details");
        System.out.println("-dm : --draw-map : Draw the whole map");
        System.out.println("-h  : --help : Show this help message");
        System.out.println("-q  : --quit : Exit the application");
    }

    public static void createRoute(){
        RouteServiceImpl routeService = new RouteServiceImpl();
        CityServiceImpl cityService = new CityServiceImpl();
        CityLogger cityLogger = new CityLogger();
        cityService.addObserver(cityLogger);

        StartLocation startLocation1 = new StartLocation();
        startLocation1.setCity(cityService.getById(1L).get());
        StartLocation startLocation2 = new StartLocation();
        startLocation2.setCity(cityService.getById(7L).get());
        StartLocation startLocation3 = new StartLocation();
        startLocation3.setCity(cityService.getById(2L).get());

        EndLocation endLocation1 = new EndLocation();
        endLocation1.setCity(cityService.getById(5L).get());
        EndLocation endLocation2 = new EndLocation();
        endLocation2.setCity(cityService.getById(6L).get());
        EndLocation endLocation3 = new EndLocation();
        endLocation3.setCity(cityService.getById(3L).get());

        Route route1 = new Route();
        route1.setStartLocation(startLocation1);
        route1.setEndLocation(endLocation1);
        Route route2 = new Route();
        route2.setStartLocation(startLocation2);
        route2.setEndLocation(endLocation2);
        Route route3 = new Route();
        route3.setStartLocation(startLocation3);
        route3.setEndLocation(endLocation3);

        routeService.create(route1);
        routeService.create(route2);
        routeService.create(route3);
    }


    public static void createCity(){
        CityServiceImpl cityService = new CityServiceImpl();
        CityConnectionServiceImpl cityConnectionService = new CityConnectionServiceImpl();
        CityLogger cityLogger = new CityLogger();
        cityService.addObserver(cityLogger);

        City city1 = new City();
        city1.setTitle("A");
        city1.setX(10.0);
        city1.setY(5.0);
        City city2 = new City();
        city2.setTitle("B");
        city2.setX(95.0);
        city2.setY(2.0);
        City city3 = new City();
        city3.setTitle("C");
        city3.setX(20.0);
        city3.setY(15.0);
        City city4 = new City();
        city4.setTitle("D");
        city4.setX(75.0);
        city4.setY(10.0);
        City city5 = new City();
        city5.setTitle("E");
        city5.setX(50.0);
        city5.setY(25.0);
        City city6 = new City();
        city6.setTitle("F");
        city6.setX(90.0);
        city6.setY(18.0);
        City city7 = new City();
        city7.setTitle("G");
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
        cityConnection1.setDistance(0.0);
        CityConnection cityConnection2 = new CityConnection();
        cityConnection2.setFirstCity(city1);
        cityConnection2.setSecondCity(city3);
        cityConnection2.setDistance(0.0);

        CityConnection cityConnection3 = new CityConnection();
        cityConnection3.setFirstCity(city7);
        cityConnection3.setSecondCity(city5);
        cityConnection3.setDistance(0.0);

        CityConnection cityConnection4 = new CityConnection();
        cityConnection4.setFirstCity(city3);
        cityConnection4.setSecondCity(city5);
        cityConnection4.setDistance(0.0);

        CityConnection cityConnection5 = new CityConnection();
        cityConnection5.setFirstCity(city3);
        cityConnection5.setSecondCity(city4);
        cityConnection5.setDistance(0.0);

        CityConnection cityConnection6 = new CityConnection();
        cityConnection6.setFirstCity(city5);
        cityConnection6.setSecondCity(city6);
        cityConnection6.setDistance(0.0);

        CityConnection cityConnection7 = new CityConnection();
        cityConnection7.setFirstCity(city4);
        cityConnection7.setSecondCity(city6);
        cityConnection7.setDistance(0.0);

        CityConnection cityConnection8 = new CityConnection();
        cityConnection8.setFirstCity(city4);
        cityConnection8.setSecondCity(city2);
        cityConnection8.setDistance(0.0);

        CityConnection cityConnection9 = new CityConnection();
        cityConnection9.setFirstCity(city2);
        cityConnection9.setSecondCity(city6);
        cityConnection9.setDistance(0.0);


        cityConnectionService.create(cityConnection1);
        cityConnectionService.create(cityConnection2);
        cityConnectionService.create(cityConnection3);
        cityConnectionService.create(cityConnection4);
        cityConnectionService.create(cityConnection5);
        cityConnectionService.create(cityConnection6);
        cityConnectionService.create(cityConnection7);
        cityConnectionService.create(cityConnection8);
        cityConnectionService.create(cityConnection9);
    }
}