package controller;

import model.*;
import model.ParcelMap;
import model.QueueofCustomers;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private final QueueofCustomers queue;
    private final ParcelMap parcelMap;
    private final Worker worker;
    private final Log log;

    public Manager() {
        queue = new QueueofCustomers();
        parcelMap = ParcelMap.getInstance(); // Use Singleton method
        worker = new Worker();
        log = Log.getInstance();


        // Hook to print summary on application exit
        Runtime.getRuntime().addShutdownHook(new Thread(this::printSummary));
    }
    public Log getLog() {
        return log;
    }

    // Getters for queue and parcelMap
    public QueueofCustomers getQueue() {
        return queue;
    }

    public ParcelMap getParcelMap() {
        return parcelMap;
    }

    // Load customers from file
    public void loadCustomers(String filename) {
        System.out.println("Loading customers from file: " + filename);
        try (var is = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (is == null) throw new NullPointerException("File not found: " + filename);
            var reader = new java.io.BufferedReader(new java.io.InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int seqNo = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String parcelId = parts[2].trim();
                queue.addCustomer(new Customer(seqNo, name, parcelId));
            }
        } catch (Exception e) {
            System.err.println("Error loading customer data: " + e.getMessage());
        }
        System.out.println("Loaded customers: " + queue.getCustomers().size());
    }

    // Load parcels from file
    public void loadParcels(String filename) {
        System.out.println("Loading parcels from file: " + filename);
        try (var is = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (is == null) throw new NullPointerException("File not found: " + filename);
            var reader = new java.io.BufferedReader(new java.io.InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String parcelId = parts[0].trim();
                int daysInDepot = Integer.parseInt(parts[1].trim());
                double weight = Double.parseDouble(parts[2].trim());
                double length = Double.parseDouble(parts[3].trim());
                double width = Double.parseDouble(parts[4].trim());
                double height = Double.parseDouble(parts[5].trim());
                parcelMap.addParcel(new Parcel(parcelId, daysInDepot, weight, length, width, height));
            }
        } catch (Exception e) {
            System.err.println("Error loading parcel data: " + e.getMessage());
        }
        System.out.println("Loaded parcels: " + parcelMap.getParcels().size());
    }

    public String processNextCustomer() {
        if (queue.isEmpty()) {
            return "No customers in the queue.";
        }

        Customer customer = queue.peekCustomer(); // Only peek, do not remove yet
        if (customer == null) {
            return "No customers in the queue.";
        }

        Parcel parcel = parcelMap.getParcelById(customer.getParcelId());
        if (parcel == null) {
            return "Parcel not found for customer: " + customer.getName();
        }

        double fee = worker.processCustomer(customer, parcel);

        // Update the queue and parcel after processing
        queue.removeCustomer(); // Remove customer from queue
        parcelMap.updateParcelStatus(parcel.getParcelId(), "collected"); // Update parcel status

        return "Processed customer: " + customer.getName() + " | Fee: £" + String.format("%.2f", fee);
    }


    public void saveReport(String filename) {
        Log.getInstance().writeReport(filename, this);
    }

    // Add a new customer to the queue
    public void addCustomer(Customer customer) {
        queue.addCustomer(customer); // Add to the queue
        System.out.println("[INFO] Added customer: " + customer.getName());
        System.out.println("Customer Details: \n- Name: " + customer.getName() + "\n- Parcel ID: " + customer.getParcelId());
    }

    public void addParcel(Parcel parcel) {
        parcelMap.addParcel(parcel);// Add to the ParcelMap
        System.out.println("[INFO] Added parcel: " + parcel.getParcelId());
        System.out.println("Parcel Details: \n- Dimensions: " + parcel.getLength() + " x " + parcel.getWidth() + " x " + parcel.getHeight() + "\n- Weight: " + parcel.getWeight() + "\n- Days in Depot: " + parcel.getDaysInDepot());
    }

    // Find a parcel by its ID
    public Parcel findParcelById(String parcelId) {
        return parcelMap.getParcelById(parcelId);
    }

    // Get all customers as a list
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(queue.getCustomers()); // Convert Queue to List
    }

    public void printSummary() {
        String summary = "\nApplication Summary\n" +
                "===================\n" +
                "Total Customers Processed: " + queue.getProcessedCount() + "\n" +
                "Total Parcels Collected: " + Log.getInstance().getCollectedParcels().size() + "\n" +
                "Total Fees Collected: £" + String.format("%.2f", Log.getInstance().getTotalFees()) + "\n";

        System.out.println(summary);
    }

    // Get all parcels as a list
    public List<Parcel> getAllParcels() {
        return List.copyOf(parcelMap.getParcels().values());
    }
}
