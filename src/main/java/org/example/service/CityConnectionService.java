package org.example.service;

import org.example.model.CityConnection;

import java.util.List;
import java.util.Optional;

public interface CityConnectionService extends GenericService<CityConnection> {
    List<CityConnection> getCityConnectionsByCityId(Long id);
}
