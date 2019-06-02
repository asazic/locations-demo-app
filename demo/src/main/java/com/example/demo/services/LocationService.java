package com.example.demo.services;

import com.example.demo.helpers.GeolocationCalculator;
import com.example.demo.models.Location;
import com.example.demo.repositories.LocationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationService implements ILocationService {
    private LocationRepository _repository;

    @Autowired
    public LocationService(LocationRepository repository) {
        _repository = repository;
    }

    public List<Location> getAll() {
        return _repository.findAll();
    }

    public Location getById(ObjectId id) {
        return _repository.findById(id);
    }

    public Location createLocation(Location location) {
        location.setId(ObjectId.get());
        _repository.save(location);
        return location;
    }

    public void modifyLocation(ObjectId id, Location location) {
        location.setId(id);
        _repository.save(location);
    }

    public void deleteLocation(ObjectId id) {
        _repository.delete(_repository.findById(id));
    }

    public List<Location> getFilteredLocations(ObjectId id, Double radius) {
        Location loc = _repository.findById(id);
        if(loc != null) {
            double boundaries[] = GeolocationCalculator.getBoundaries(loc.getLat(), loc.getLng(), radius);
            Location[] locations = _repository.findByLatBetweenAndLngBetween(boundaries[0], boundaries[1], boundaries[2], boundaries[3]);
            List<Location> locationsWithinDistance = new ArrayList<>();
            for (int i = 0; i < locations.length; i++) {
                boolean isInDistance = GeolocationCalculator.isWithinDistance(loc.getLat(), loc.getLng(), locations[i].getLat(), locations[i].getLng(), radius);
                if(isInDistance) {
                    locationsWithinDistance.add(locations[i]);
                }
            }
            return locationsWithinDistance;
        } else {
            return new ArrayList<>();
        }
    }
}
