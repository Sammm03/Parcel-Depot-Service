package controller;

import model.Customer;
import model.Parcel;
import model.QueueofCustomers;
import model.ParcelMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Manager {
    private QueueofCustomers queue;
    private ParcelMap parcelMap;

    public Manager() {
        queue = new QueueofCustomers();
        parcelMap = new ParcelMap();
    }

    public QueueofCustomers getQueue() {
        return queue;
    }

    public ParcelMap getParcelMap() {
        return parcelMap;
    }

    public void loadCustomers(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                queue.addCustomer(new Customer(Integer.parseInt(parts[0]), parts[1]));
                Log.getInstance().logEvent("Customer loaded: " + parts[1]);
            }
        }
    }

    public void loadParcels(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                parcelMap.addParcel(new Parcel(parts[0], Float.parseFloat(parts[1]),
                        Integer.parseInt(parts[2]), parts[3]));
                Log.getInstance().logEvent("Parcel loaded: " + parts[0]);
            }
        }
    }
}
