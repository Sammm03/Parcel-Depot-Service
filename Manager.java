package service;

import model.Customer;
import model.Parcel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Manager {
    private service.QueueofCustomers queue;
    private service.ParcelMap parcelMap;

    public Manager() {
        queue = new service.QueueofCustomers();
        parcelMap = new service.ParcelMap();
    }

    public void loadCustomers(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                queue.addCustomer(new Customer(Integer.parseInt(parts[0]), parts[1]));
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
            }
        }
    }

    public void initializeSystem() {
        // Add Worker and GUI initialization
    }
}
