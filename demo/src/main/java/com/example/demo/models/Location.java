package com.example.demo.models;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Location {

    @Id
    private ObjectId id;

    private Double lat;
    private Double lng;
    private String locationName;

    public Location(ObjectId id, Double lat, Double lng, String locationName) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.locationName = locationName;
    }

    public String getId() { return id.toHexString(); }
    public void setId(ObjectId id) { this.id = id; }

    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }

    public Double getLng() { return lng; }
    public void setLng(Double lng) { this.lng = lng; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
}
