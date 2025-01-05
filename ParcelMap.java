package model;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private Map<String, Parcel> parcels;

    public ParcelMap() {
        parcels = new HashMap<>();
    }

    // Add a parcel to the map
    public void addParcel(Parcel parcel) {
        parcels.put(parcel.getParcelId(), parcel); // Parcel ID as the key
    }

    // Get a parcel by its ID
    public Parcel getParcelById(String parcelId) {
        return parcels.get(parcelId); // Retrieve the Parcel object
    }

    // Get all parcels
    public Map<String, Parcel> getParcels() {
        return parcels;
    }
}
