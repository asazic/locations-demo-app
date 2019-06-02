package com.example.demo.controllers;

import com.example.demo.models.Location;
import com.example.demo.services.LocationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LocationsController {
    private LocationService _locationService;

    @Autowired
    public LocationsController(LocationService locationService) {
        _locationService = locationService;
    }

    @RequestMapping(value="/locations", method = RequestMethod.GET)
    public List<Location> getAllLocations() {
        return _locationService.getAll();
    }

    @RequestMapping(value="/locations/{id}", method = RequestMethod.GET)
    public Location getSpecificLocation(@PathVariable("id") ObjectId id) {
        return _locationService.getById(id);
    }

    @RequestMapping(value="/locations", method = RequestMethod.POST)
    public Location addLocation(@Valid @RequestBody Location location) {
        return _locationService.createLocation(location);
    }

    @RequestMapping(value="/locations/{id}", method = RequestMethod.PUT)
    public void modifyByLocationId(@PathVariable("id") ObjectId id, @Valid @RequestBody Location location) {
        _locationService.modifyLocation(id, location);
    }

    @RequestMapping(value="/locations/{id}", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable ObjectId id) {
        _locationService.deleteLocation(id);
    }

    @RequestMapping(value="/locations/filtered", method = RequestMethod.GET)
    public List<Location> getFiltered(@RequestParam ObjectId addressId, @RequestParam Double radius) {
        return _locationService.getFilteredLocations(addressId, radius);
    }
}
