package model;

public class Parcel {
    private String parcelId;
    private int daysInDepot;
    private double weight;
    private double length;
    private double width;
    private double height;
    private String status;

    public Parcel(String parcelId, int daysInDepot, double weight, double length, double width, double height) {
        this.parcelId = parcelId;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.status = "waiting";
    }

    // Getters
    public String getParcelId() {
        return parcelId;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    // New method to get dimensions as a formatted string
    public String getDimensions() {
        return length + " x " + width + " x " + height;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "parcelId='" + parcelId + '\'' +
                ", daysInDepot=" + daysInDepot +
                ", weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", status='" + status + '\'' +
                '}';
    }
}
