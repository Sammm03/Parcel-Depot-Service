package model;

import controller.Manager;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to maintain logs for parcel depot management.
 * Tracks processed parcels, added customers, and events.
 */
public class Log {
    private static Log instance; // Singleton instance
    private final List<String> collectedParcels = new ArrayList<>(); // Log of processed parcels
    private final List<String> events = new ArrayList<>(); // General events log
    private double totalFees = 0; // Total fees collected

    private Log() {
        // Private constructor to prevent instantiation
    }

    /**
     * Retrieves the singleton instance of the Log class.
     *
     * @return Log instance.
     */
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    /**
     * Adds a processed parcel to the log and updates total fees.
     *
     * @param event Parcel collection event details.
     * @param fee   Fee collected for the parcel.
     */
    public void addCollectedParcel(String event, double fee) {
        collectedParcels.add(event);
        totalFees += fee;
    }

    /**
     * Adds a custom event to the log.
     *
     * @param event Description of the event.
     */
    public void addEvent(String event) {
        events.add(event);
    }

    /**
     * Retrieves the total fees collected.
     *
     * @return Total fees.
     */
    public double getTotalFees() {
        return totalFees;
    }

    /**
     * Returns a copy of the collected parcels log.
     *
     * @return List of collected parcels.
     */
    public List<String> getCollectedParcels() {
        return new ArrayList<>(collectedParcels);
    }

    /**
     * Generates and writes a report to the specified file.
     *
     * @param filename The name of the file where the report will be saved.
     * @param manager  Reference to the Manager for additional data.
     */
    public void writeReport(String filename, Manager manager) {
        StringBuilder report = new StringBuilder();
        report.append("Parcel Depot Management Report\n");
        report.append("=============================\n\n");
        report.append("Total Customers Processed: ").append(manager.getQueue().getProcessedCount()).append("\n");
        report.append("Total Parcels Collected: ").append(collectedParcels.size()).append("\n");
        report.append("Total Fees Collected: £").append(String.format("%.2f", totalFees)).append("\n\n");

        // List processed parcels
        report.append("Processed Parcels:\n");
        report.append("------------------\n");
        for (String event : collectedParcels) {
            report.append(event).append("\n");
        }

        // Summary statistics
        report.append("\nSummary Statistics:\n");
        report.append("-------------------\n");
        report.append("Total Parcels in Depot: ").append(manager.getParcelMap().getParcels().size()).append("\n");
        report.append("Parcels Waiting: ").append(manager.getParcelMap().getWaitingCount()).append("\n");
        report.append("Parcels Collected: ").append(collectedParcels.size()).append("\n\n");

        report.append("Thank you for using Parcel Depot Management System!\n");
        report.append("Generated on: ").append(LocalDateTime.now()).append("\n");

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(report.toString());
        } catch (IOException e) {
            System.err.println("Error writing report: " + e.getMessage());
        }
    }
}

