package com.example.demo.services;

import com.example.demo.models.Location;
import org.bson.types.ObjectId;

import java.util.List;

public interface ILocationService {
    List<Location> getAll();
    Location getById(ObjectId id);
    Location createLocation(Location location);
    void modifyLocation(ObjectId id, Location location);
    void deleteLocation(ObjectId id);
    List<Location> getFilteredLocations(ObjectId id, Double radius);
}
