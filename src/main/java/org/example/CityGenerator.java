package org.example;

import org.example.model.City;
import org.example.model.CityConnection;
import org.example.service.CityConnectionService;
import org.example.service.CityService;

import java.util.*;

public class CityGenerator {

    public static void generateRandomCities(CityService cityService, CityConnectionService cityConnectionService) {
        Random random = new Random();
        Set<String> uniqueTitles = new HashSet<>();
        List<City> cities = new ArrayList<>();

        while (cities.size() < 15) {
            String title = generateUniqueTitle(uniqueTitles, random);
            double x = 1.0 + (95.0 - 1.0) * random.nextDouble();
            double y = 1.0 + (95.0 - 1.0) * random.nextDouble();

            City city = new City();
            city.setTitle(title);
            city.setX(x);
            city.setY(y);

            cityService.create(city);
            cities.add(city);
        }

        for (City city : cities) {
            Set<City> connectedCities = new HashSet<>();

            while (connectedCities.size() < 2) {
                City randomCity = cities.get(random.nextInt(cities.size()));
                if (!randomCity.equals(city)) {
                    connectedCities.add(randomCity);
                }
            }

            for (City connectedCity : connectedCities) {
                CityConnection connection = new CityConnection();
                connection.setFirstCity(city);
                connection.setSecondCity(connectedCity);
                connection.setDistance(calculateDistance(city, connectedCity));
                cityConnectionService.create(connection);
            }
        }

        System.out.println("15 unique cities with connections were generated successfully!");
    }

    private static String generateUniqueTitle(Set<String> uniqueTitles, Random random) {
        String title;
        do {
            title = String.valueOf((char) ('A' + random.nextInt(26)));
        } while (!uniqueTitles.add(title));
        return title;
    }

    private static double calculateDistance(City city1, City city2) {
        double deltaX = city1.getX() - city2.getX();
        double deltaY = city1.getY() - city2.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public static void createCity(CityService cityService, CityConnectionService cityConnectionService){

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