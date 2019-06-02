package com.example.demo.helpers;

public class GeolocationCalculator {
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
    private static final double MILE_FACTOR = 0.621371;

    public static double distanceInKM(double startLat, double startLong,
                                      double endLat, double endLong) {

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);
        startLong = Math.toRadians(startLong);
        endLong = Math.toRadians(endLong);

        double distance = Math.acos(Math.sin(startLat) * Math.sin(endLat) +
                Math.cos(startLat) * Math.cos(endLat) * Math.cos(startLong - endLong));

        return EARTH_RADIUS * distance;
    }

    public static double distanceInMiles(double startLat, double startLong,
                                         double endLat, double endLong) {
        double km = distanceInKM(startLat, startLong, endLat, endLong);
        return km * MILE_FACTOR;
    }

    public static double[] getBoundaries(double centerLat, double centerLong, double radius) {
        double minLat = centerLat - Math.toDegrees(radius / EARTH_RADIUS);
        double maxLat = centerLat + Math.toDegrees(radius / EARTH_RADIUS);

        double minLong = centerLong - Math.toDegrees(radius / EARTH_RADIUS);
        double maxLong = centerLong + Math.toDegrees(radius / EARTH_RADIUS);

        double boundaries[] = {minLat, maxLat, minLong, maxLong};
        return boundaries;
    }

    public static boolean isWithinDistance(double centerLat, double centerLong, double latitude, double longitude, double distance) {
        double resultDistance = distanceInMiles(centerLat, centerLong, latitude, longitude);
        return resultDistance <= distance;
    }
}
