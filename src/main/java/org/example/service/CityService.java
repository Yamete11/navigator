package org.example.service;

import org.example.model.City;
import org.example.service.observer.Observable;

import java.util.List;
import java.util.Optional;

public interface CityService extends GenericService<City>, Observable {
    Optional<City> getCityByTitle(String title);
    List<City> getCitiesNotInConnections();

}
