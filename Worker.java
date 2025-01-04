package controller;

import model.Customer;
import model.Parcel;
import model.QueueofCustomers;
import model.ParcelMap;

public class Worker {
    private QueueofCustomers queue;
    private ParcelMap parcelMap;

    public Worker(QueueofCustomers queue, ParcelMap parcelMap) {
        this.queue = queue;
        this.parcelMap = parcelMap;
    }

    public void processNextCustomer() {
        Customer customer = queue.removeCustomer();
        if (customer != null) {
            Parcel parcel = parcelMap.getParcelById(String.valueOf(customer.getSequenceNumber()));
            if (parcel != null) {
                float fee = parcel.calculateFee();
                parcel.setStatus("collected");

                Log.getInstance().logEvent("Processed customer: " + customer.getName() +
                        ", Parcel ID: " + parcel.getParcelId() +
                        ", Fee: " + fee);
            }
        }
    }
}
