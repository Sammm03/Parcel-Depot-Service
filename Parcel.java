package model;

public class Parcel {
    private String parcelId;
    private float weight;
    private int daysInDepot;
    private String status;

    public Parcel(String parcelId, float weight, int daysInDepot, String status) {
        this.parcelId = parcelId;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = status;
    }

    public float calculateFee() {
        float feePerKg = 10.0f;
        float dailyStorageFee = 2.0f;
        return (weight * feePerKg) + (daysInDepot * dailyStorageFee);
    }

    public String getParcelId() {
        return parcelId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
