package controller;

import model.*;

public class Worker {

    public Worker() {
    }

    public double processCustomer(Customer customer, Parcel parcel) {
        Log log = Log.getInstance();

        // Add an event to the log
        log.addEvent("Processing customer: " + customer.getName() + ", Parcel ID: " + parcel.getParcelId());

        // Calculate fee based on parcel attributes
        double fee = calculateFee(parcel);
        log.addCollectedParcel("Parcel " + parcel.getParcelId() + " collected by customer " + customer.getName() + " | Fee: £" + String.format("%.2f", fee), fee);

        return fee;
    }


    private double calculateFee(Parcel parcel) {
        double baseFee = 5.0;
        double weightFee = parcel.getWeight() * 0.5;
        double dimensionFee = parcel.getLength() * parcel.getWidth() * parcel.getHeight() * 0.01;
        double daysInDepotFee = parcel.getDaysInDepot() * 0.2;

        double totalFee = baseFee + weightFee + dimensionFee + daysInDepotFee;

        if (parcel.getParcelId().startsWith("X")) {
            totalFee *= 0.9; // 10% discount on PracelID Starts with "X"
        }
        if (parcel.getLength() * parcel.getWidth() * parcel.getHeight() < 50) {
            totalFee -= 2.0; // £2 off
        }
        if (parcel.getWeight() < 2.0) {
            totalFee *= 0.95; // 5% discount
        }

        return Math.max(totalFee, 0.0);
    }
}
