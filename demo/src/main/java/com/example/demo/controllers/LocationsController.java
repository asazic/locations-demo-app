package com.example.demo.controllers;

import com.example.demo.models.Location;
import com.example.demo.repositories.LocationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LocationsController {
    private LocationRepository _repository;

    @Autowired
    public LocationsController(LocationRepository repository) {
        _repository = repository;
    }

    @RequestMapping(value="/locations", method = RequestMethod.GET)
    public List<Location> getAllLocations() {
        return _repository.findAll();
    }

    @RequestMapping(value="/locations/{id}", method = RequestMethod.GET)
    public Location getSpecificLocation(@PathVariable("id") ObjectId id) {
        return _repository.findById(id);
    }

    @RequestMapping(value="/locations", method = RequestMethod.POST)
    public Location addLocation(@Valid @RequestBody Location location) {
        location.setId(ObjectId.get());
        _repository.save(location);
        return location;
    }

    @RequestMapping(value="/locations/{id}", method = RequestMethod.PUT)
    public void modifyByLocationId(@PathVariable("id") ObjectId id, @Valid @RequestBody Location location) {
        location.setId(id);
        _repository.save(location);
    }

    @RequestMapping(value="/locations/{id}", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable ObjectId id) {
        _repository.delete(_repository.findById(id));
    }

    @RequestMapping(value="/locations/filtered", method = RequestMethod.GET)
    public Location[] getFiltered(@RequestParam ObjectId addressId, @RequestParam Double radius) {
        Location loc = _repository.findById(addressId);
        if(loc != null) {
            Double latLow = loc.getLat() - radius/2;
            Double latHigh = loc.getLat() + radius/2;
            Double lngLow = loc.getLng() - radius/2;
            Double lngHigh = loc.getLng() + radius/2;
            return _repository.findByLatBetweenAndLngBetween(latLow, latHigh, lngLow, lngHigh);
        } else {
            Location[] res = {};
            return res;
        }
    }
}
