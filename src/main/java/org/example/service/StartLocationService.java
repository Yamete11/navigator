package org.example.service;

import org.example.model.StartLocation;

public interface StartLocationService extends GenericService<StartLocation> {
    void deleteByCityId(Long id);
}
