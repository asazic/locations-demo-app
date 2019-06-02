package com.example.demo.repositories;

import com.example.demo.models.Location;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
    Location findById(ObjectId _id);
    Location[] findByLocationName(String locationName);
    Location[] findByLatBetweenAndLngBetween(Double latLow, Double latHigh, Double lngLow, Double lngHigh);
}
