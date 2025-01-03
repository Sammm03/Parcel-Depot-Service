package service;

import model.Customer;
import model.Parcel;
import util.Log;

public class Worker {
    private service.QueueofCustomers queue;
    private service.ParcelMap parcelMap;

    public Worker(service.QueueofCustomers queue, service.ParcelMap parcelMap) {
        this.queue = queue;
        this.parcelMap = parcelMap;
    }

    public void processNextCustomer() {
        Customer customer = queue.removeCustomer();
        Parcel parcel = parcelMap.getParcelById(customer.getSequenceNumber() + ""); // Example logic

        float fee = parcel.calculateFee();
        parcel.setStatus("collected");

        Log.getInstance().logEvent("Processed customer: " + customer.getName() +
                ", Parcel ID: " + parcel.getParcelId() +
                ", Fee: " + fee);
    }
}
