package view;

import controller.Manager;
import controller.Worker;
import model.Customer;
import model.Parcel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Manager manager;
    private Worker worker;

    private DefaultListModel<String> customerListModel;
    private DefaultListModel<String> parcelListModel;
    private JTextArea logArea;

    public MainFrame(Manager manager, Worker worker) {
        this.manager = manager;
        this.worker = worker;

        setTitle("Depot Parcel Processing System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Customer Panel
        customerListModel = new DefaultListModel<>();
        JList<String> customerList = new JList<>(customerListModel);
        JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBorder(BorderFactory.createTitledBorder("Customer Queue"));
        customerPanel.add(new JScrollPane(customerList), BorderLayout.CENTER);

        // Parcel Panel
        parcelListModel = new DefaultListModel<>();
        JList<String> parcelList = new JList<>(parcelListModel);
        JPanel parcelPanel = new JPanel(new BorderLayout());
        parcelPanel.setBorder(BorderFactory.createTitledBorder("Parcels"));
        parcelPanel.add(new JScrollPane(parcelList), BorderLayout.CENTER);

        // Log Panel
        logArea = new JTextArea();
        logArea.setEditable(false);
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder("System Log"));
        logPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton processButton = new JButton("Process Customer");
        processButton.addActionListener(e -> processNextCustomer());

        buttonPanel.add(processButton);

        // Add Components
        add(customerPanel, BorderLayout.WEST);
        add(parcelPanel, BorderLayout.CENTER);
        add(logPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        // Update Panels
        updateCustomerList();
        updateParcelList();
    }

    private void processNextCustomer() {
        worker.processNextCustomer();
        updateCustomerList();
        updateParcelList();
        logArea.append("Processed next customer.\n");
    }

    private void updateCustomerList() {
        customerListModel.clear();
        for (Customer customer : manager.getQueue().getCustomers()) {
            customerListModel.addElement(customer.getSequenceNumber() + " - " + customer.getName());
        }
    }

    private void updateParcelList() {
        parcelListModel.clear();
        for (Parcel parcel : manager.getParcelMap().getParcels().values()) {
            parcelListModel.addElement(parcel.getParcelId() + " - " + parcel.getStatus());
        }
    }
}
