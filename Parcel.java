package model;

/**
 * Represents a parcel in the depot.
 * Includes details such as dimensions, weight, days in depot, and collection status.
 */
public class Parcel {
    private final String parcelId; // Unique identifier for the parcel
    private final double length;  // Length of the parcel
    private final double width;   // Width of the parcel
    private final double height;  // Height of the parcel
    private final double weight;  // Weight of the parcel
    private final int daysInDepot;      // Number of days the parcel has been in the depot
    private String status;        // Status of the parcel: "waiting" or "collected"

    /**
     * Constructor to initialize a parcel with its attributes.
     *
     * @param parcelId    Unique ID of the parcel.
     * @param daysInDepot Number of days in the depot.
     * @param weight      Weight of the parcel.
     * @param length      Length of the parcel.
     * @param width       Width of the parcel.
     * @param height      Height of the parcel.
     */
    public Parcel(String parcelId, int daysInDepot, double weight, double length, double width, double height) {
        this.parcelId = parcelId;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.status = "waiting"; // Default status is "waiting"
    }

    /**
     * Retrieves the unique ID of the parcel.
     *
     * @return Parcel ID.
     */
    public String getParcelId() {
        return parcelId;
    }

    /**
     * Retrieves the length of the parcel.
     *
     * @return Length of the parcel.
     */
    public double getLength() {
        return length;
    }

    /**
     * Retrieves the width of the parcel.
     *
     * @return Width of the parcel.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Retrieves the height of the parcel.
     *
     * @return Height of the parcel.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Retrieves the weight of the parcel.
     *
     * @return Weight of the parcel.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Retrieves the number of days the parcel has been in the depot.
     *
     * @return Days in depot.
     */
    public int getDaysInDepot() {
        return daysInDepot;
    }

    /**
     * Retrieves the current status of the parcel.
     *
     * @return Parcel status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the status of the parcel.
     *
     * @param status New status ("waiting" or "collected").
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the parcel for display purposes.
     *
     * @return Parcel details as a string.
     */
    @Override
    public String toString() {
        return "Parcel ID: " + parcelId +
                ", Dimensions: " + length + " x " + width + " x " + height +
                ", Weight: " + weight +
                ", Days in Depot: " + daysInDepot +
                ", Status: " + status;
    }
}
