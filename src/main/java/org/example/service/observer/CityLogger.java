package org.example.service.observer;

import org.example.model.City;

public class CityLogger implements Observer {

    @Override
    public void update(CityEventType eventType, City city) {
        switch (eventType) {
            case CITY_ADDED -> System.out.println("City added: " + city);
            case CITY_UPDATED -> System.out.println("City updated: " + city);
            case CITY_DELETED -> System.out.println("City deleted: " + city);
        }
    }
}
