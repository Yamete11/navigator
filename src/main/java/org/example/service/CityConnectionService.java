package org.example.service;

import org.example.model.CityConnection;

import java.util.List;

public interface CityConnectionService extends GenericService<CityConnection> {
    List<CityConnection> getCityConnectionsByCityId(Long id);
    void deleteByCityId(Long id);

}
