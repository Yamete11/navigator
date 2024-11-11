package org.example.service;

import org.example.model.EndLocation;

public interface EndLocationService extends GenericService<EndLocation> {
    void deleteByCityId(Long id);
}
