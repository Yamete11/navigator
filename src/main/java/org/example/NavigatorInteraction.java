package org.example;

import org.example.model.City;
import org.example.model.CityConnection;
import org.example.model.Route;
import org.example.model.StartLocation;
import org.example.model.EndLocation;
import org.example.service.implementation.*;
import org.example.ui.GraphDrawer;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class NavigatorInteraction {
    private final CityServiceImpl cityService;
    private final RouteServiceImpl routeService;
    private final Scanner scanner;

    private boolean isRunning = true;

    public NavigatorInteraction(
            CityServiceImpl cityService,
            RouteServiceImpl routeService) {
        this.cityService = cityService;
        this.routeService = routeService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        displayWelcomeMessage();
        //System.out.println(new GraphDrawer().draw());
        while (isRunning) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim();
            handleCommand(command);
        }
    }

    private void handleCommand(String command) {
        switch (command) {
            case "-ac", "--add-city" -> handleAddCity();
            case "-ar", "--add-route" -> handleAddRoute();
            case "-dc", "--delete-city" -> handleDeleteCity();
            case "-u", "--update-city" -> handleUpdateCity();
            case "-dm", "--draw-map" -> handleDrawMap();
            case "-h", "--help" -> displayHelpMessage();
            case "-q", "--quit" -> {
                isRunning = false;
                System.out.println("Exiting the Navigator application.");
            }
            default -> System.out.println("Unknown command: " + command);
        }
    }
    private void handleDrawMap(){
        //System.out.println(new GraphDrawer().draw());
    }

    private void handleAddCity() {
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

        double x, y;
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
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            try {
                System.out.println("Rolling back city creation...");
                cityService.deleteById(city.getId());
                System.out.println("City removed successfully.");
            } catch (Exception rollbackException) {
                System.err.println("Error during rollback: " + rollbackException.getMessage());
            }
        }
    }

    private void handleAddRoute() {
        List<City> cityList = cityService.findAll();

        System.out.println("List of cities:");
        for (City city : cityList) {
            System.out.println("ID: " + city.getId() + ", Name: " + city.getTitle());
        }

        Scanner scanner = new Scanner(System.in);

        long cityId1, cityId2;

        while (true) {
            System.out.print("Enter the ID of the first city: ");
            try {
                cityId1 = Long.parseLong(scanner.nextLine().trim());
                Optional<City> city1 = cityService.getById(cityId1);
                if (city1.isPresent()) {
                    break;
                } else {
                    System.err.println("City with ID " + cityId1 + " not found. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid city ID.");
            }
        }

        while (true) {
            System.out.print("Enter the ID of the second city: ");
            try {
                cityId2 = Long.parseLong(scanner.nextLine().trim());
                Optional<City> city2 = cityService.getById(cityId2);
                if (city2.isPresent()) {
                    if (cityId1 != cityId2) {
                        break;
                    } else {
                        System.err.println("Cities cannot be the same. Please choose a different city.");
                    }
                } else {
                    System.err.println("City with ID " + cityId2 + " not found. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid city ID.");
            }
        }

        System.out.println("Cities selected: " + cityService.getById(cityId1).get().getTitle() + " and " + cityService.getById(cityId2).get().getTitle());

        //GraphDrawer graphDrawer = new GraphDrawer();
        Route existingRoute = routeService.checkIfRouteExists(cityId1, cityId2);
        if (existingRoute != null) {
            //System.out.println(graphDrawer.drawRoute(routeService.getCityConnectionsByRouteId(existingRoute.getId())));
            System.out.println("A route already exists between these two cities.");
            return;
        }

        StartLocation startLocation = new StartLocation();
        startLocation.setCity(cityService.getById(cityId1).get());

        EndLocation endLocation = new EndLocation();
        endLocation.setCity(cityService.getById(cityId2).get());

        Route route = new Route();
        route.setStartLocation(startLocation);
        route.setEndLocation(endLocation);
        route.setTotalDistance(0.0);

        routeService.create(route);
        routeService.getCityConnectionsByRouteId(route.getId()).forEach(System.out::println);
        //System.out.println(graphDrawer.drawRoute(routeService.getCityConnectionsByRouteId(route.getId())));

        System.out.println("Route added successfully!");
    }

    private void handleDeleteCity() {
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

    private void handleUpdateCity() {
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

        double x, y;
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

    private void displayWelcomeMessage() {
        System.out.println("========================== Navigator Application ==========================");
        System.out.println("Welcome to the Navigator application! You can manage cities and routes, find optimal paths, and view city maps.");
        displayHelpMessage();
    }

    private void displayHelpMessage() {
        System.out.println("Available Commands:");
        System.out.println("-ac : --add-city : Add a new city");
        System.out.println("-ar : --add-route : Add a new route");
        System.out.println("-dc : --delete-city : Delete an existing city");
        System.out.println("-u  : --update-city : Update an existing city");
        System.out.println("-dm : --draw-map : Display the map of cities");
        System.out.println("-h  : --help : Display this help message");
        System.out.println("-q  : --quit : Exit the application");
    }
}
