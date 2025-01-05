package view;

import controller.Manager;
import model.Customer;
import model.Parcel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable customerTable;
    private JTable parcelTable;
    private Manager manager;

    public MainFrame(List<Customer> customers, List<Parcel> parcels, Manager manager) {
        this.manager = manager;

        setTitle("Parcel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Initialize Tables
        initializeCustomerTable(customers);
        initializeParcelTable(parcels);

        // Add Buttons
        JButton processButton = new JButton("Process Next Customer");
        processButton.addActionListener(e -> processCustomer());

        // Add Components
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(customerTable), new JScrollPane(parcelTable));
        splitPane.setDividerLocation(300);

        add(new JLabel("Parcel Management System", SwingConstants.CENTER), BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(processButton, BorderLayout.SOUTH);

        setVisible(true); // Ensure GUI is visible
    }

    private void initializeCustomerTable(List<Customer> customers) {
        String[] customerColumns = {"Seq No", "Name", "Parcel ID"};
        Object[][] customerData = new Object[customers.size()][3];

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            customerData[i][0] = customer.getSequenceNumber();
            customerData[i][1] = customer.getName();
            customerData[i][2] = customer.getParcelId();
        }

        customerTable = new JTable(customerData, customerColumns);
    }

    private void initializeParcelTable(List<Parcel> parcels) {
        String[] parcelColumns = {"Parcel ID", "Days in Depot", "Weight", "Dimensions", "Status"};
        Object[][] parcelData = new Object[parcels.size()][5];

        for (int i = 0; i < parcels.size(); i++) {
            Parcel parcel = parcels.get(i);
            parcelData[i][0] = parcel.getParcelId();
            parcelData[i][1] = parcel.getDaysInDepot();
            parcelData[i][2] = parcel.getWeight();
            parcelData[i][3] = parcel.getDimensions();
            parcelData[i][4] = parcel.getStatus();
        }

        parcelTable = new JTable(parcelData, parcelColumns);
    }

    private void processCustomer() {
        String message = manager.processNextCustomer();
        JOptionPane.showMessageDialog(this, message);

        // Refresh GUI tables
        updateCustomerTable();
        updateParcelTable();
    }

    private void updateCustomerTable() {
        List<Customer> customers = new ArrayList<>(manager.getQueue().getCustomers());
        initializeCustomerTable(customers);
        revalidate();
        repaint();
    }

    private void updateParcelTable() {
        List<Parcel> parcels = new ArrayList<>(manager.getParcelMap().getParcels().values());
        initializeParcelTable(parcels);
        revalidate(); // Refresh GUI components
        repaint();
    }

}
