package model;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private Map<String, Parcel> parcels = new HashMap<>();

    public void addParcel(Parcel parcel) {
        parcels.put(parcel.getParcelId(), parcel);
    }

    public Parcel getParcelById(String parcelId) {
        return parcels.get(parcelId);
    }

    public void removeParcel(String parcelId) {
        parcels.remove(parcelId);
    }

    public Map<String, Parcel> getParcels() {
        return parcels;
    }
}
