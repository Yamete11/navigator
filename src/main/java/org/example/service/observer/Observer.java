package org.example.service.observer;

import org.example.model.City;

public interface Observer {
    void update(CityEventType eventType, City city);
}
