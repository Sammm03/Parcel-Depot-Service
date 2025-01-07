package model;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private static ParcelMap instance;
    private final Map<String, Parcel> parcels;

    public int getWaitingCount() {
        return (int) parcels.values().stream()
                .filter(parcel -> "waiting".equalsIgnoreCase(parcel.getStatus()))
                .count();
    }

    // Private constructor for Singleton pattern
    private ParcelMap() {
        parcels = new HashMap<>();
    }

    // Static method to get the single instance of ParcelMap
    public static synchronized ParcelMap getInstance() {
        if (instance == null) {
            instance = new ParcelMap();
        }
        return instance;
    }

    // Add a parcel to the map
    public void addParcel(Parcel parcel) {
        parcels.put(parcel.getParcelId(), parcel);
    }

    // Get a parcel by its ID
    public Parcel getParcelById(String parcelId) {
        return parcels.get(parcelId);
    }
    // Update parcel Status
    public void updateParcelStatus(String parcelId, String status) {
        Parcel parcel = parcels.get(parcelId);
        if (parcel != null) {
            parcel.setStatus(status);
        }
    }

    // Get all parcels
    public Map<String, Parcel> getParcels() {
        return parcels;
    }
    public int getCollectedCount() {
        // Count parcels with status "collected"
        return (int) parcels.values().stream()
                .filter(parcel -> "collected".equalsIgnoreCase(parcel.getStatus()))
                .count();
    }

}
