package org.example.service.observer;

import org.example.model.City;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(CityEventType eventType, City city);
}
