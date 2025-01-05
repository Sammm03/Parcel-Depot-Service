package controller;

import model.*;
import model.Log;

public class Worker {
    private QueueofCustomers queue;
    private ParcelMap parcelMap;

    public Worker(QueueofCustomers queue, ParcelMap parcelMap) {
        this.queue = queue;
        this.parcelMap = parcelMap;
    }

    public double processCustomer(Customer customer, Parcel parcel) {
        // Calculate the fee
        double fee = calculateFee(parcel);

        // Update parcel status
        if (parcel != null) {
            parcel.setStatus("collected"); // Change the status to "collected"
        } else {
            System.err.println("Parcel not found for customer: " + customer.getName());
        }

        // Remove the customer from the queue
        queue.removeCustomer();

        // Log the event
        Log.getInstance().addEvent("Processed customer: " + customer.getName() +
                ", Parcel ID: " + (parcel != null ? parcel.getParcelId() : "N/A") +
                ", Fee: $" + fee);

        return fee;
    }



    private double calculateFee(Parcel parcel) {
        double baseFee = 5.0;
        double weightFee = parcel.getWeight() * 0.5;
        double dimensionFee = parcel.getLength() * parcel.getWidth() * parcel.getHeight() * 0.01;
        double daysInDepotFee = parcel.getDaysInDepot() * 0.2;

        double totalFee = baseFee + weightFee + dimensionFee + daysInDepotFee;

        // Apply discounts
        if (parcel.getParcelId().startsWith("X")) {
            totalFee *= 0.9; // 10% discount
        }
        if (parcel.getLength() * parcel.getWidth() * parcel.getHeight() < 50) {
            totalFee -= 2.0; // $2 off
        }
        if (parcel.getWeight() < 2.0) {
            totalFee *= 0.95; // 5% discount
        }

        return Math.max(totalFee, 0.0); // Ensure fee is non-negative
    }
}
