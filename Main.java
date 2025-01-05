package view;

import model.Customer; // Import the Customer class
import model.Parcel;   // Import the Parcel class
import controller.Manager; // Import the Manager class
import model.QueueofCustomers;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        // Load data
        manager.loadCustomers("resources/Custs.csv");
        manager.loadParcels("resources/Parcels.csv");

        // Debug loaded data
        System.out.println("Customers Loaded: " + manager.getQueue().getCustomers().size());
        System.out.println("Parcels Loaded: " + manager.getParcelMap().getParcels().size());

        // Prepare data for GUI
        List<Customer> customerList = new ArrayList<>(manager.getQueue().getCustomers());
        List<Parcel> parcelList = new ArrayList<>(manager.getParcelMap().getParcels().values());

        // Debug data before GUI
        System.out.println("Passing to GUI:");
        System.out.println("Customers:");
        System.out.println("Seq no. - Name,ParcelID");
        customerList.forEach(customer -> System.out.println(customer.getSequenceNumber() + " - " + customer.getName() + "," +customer.getParcelId()));
        System.out.println("Parcels:");
        System.out.println("ParcelID - Days in Depot, Weight, Dimentions");
        parcelList.forEach(parcel -> System.out.println(parcel.getParcelId() + " - " + parcel.getDaysInDepot()+ ", " + parcel.getWeight() + ", " + parcel.getDimensions()));

        // Launch GUI
        SwingUtilities.invokeLater(() -> new MainFrame(customerList, parcelList, manager));
    }

}
