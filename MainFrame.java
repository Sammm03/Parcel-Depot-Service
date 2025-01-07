package view;

import controller.Manager;
import model.Customer;
import model.Parcel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable customerTable;
    private JTable parcelTable;
    private final Manager manager;
    private final JLabel summaryLabel;

    public MainFrame(List<Customer> customers, List<Parcel> parcels, Manager manager) {
        this.manager = manager;
        setTitle("Parcel Depot Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize tables
        initializeCustomerTable(customers);
        initializeParcelTable(parcels);

        // Create panels for tables
        JPanel customerPanel = new JPanel(new BorderLayout());
        JLabel customerLabel = new JLabel("Customer List", SwingConstants.CENTER);
        customerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        customerPanel.add(customerLabel, BorderLayout.NORTH);
        customerPanel.add(new JScrollPane(customerTable), BorderLayout.CENTER);

        JPanel parcelPanel = new JPanel(new BorderLayout());
        JLabel parcelLabel = new JLabel("Parcel List", SwingConstants.CENTER);
        parcelLabel.setFont(new Font("Arial", Font.BOLD, 14));
        parcelPanel.add(parcelLabel, BorderLayout.NORTH);
        parcelPanel.add(new JScrollPane(parcelTable), BorderLayout.CENTER);

        customerTable.setDefaultRenderer(Object.class, new AlternatingRowRenderer());
        parcelTable.setDefaultRenderer(Object.class, new AlternatingRowRenderer());

        // Create button panel
        JPanel buttonPanel = new JPanel();
        addProcessCustomerButton(buttonPanel);
        addAddCustomerButton(buttonPanel);
        addAddParcelButton(buttonPanel);
        addFindParcelButton(buttonPanel);
        addGenerateReportButton(buttonPanel);

        // Add summary label
        summaryLabel = new JLabel();
        updateSummary(); // Initialize with current summary data

        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
        summaryPanel.add(summaryLabel, BorderLayout.CENTER);

        // Add all components to the frame
        add(customerPanel, BorderLayout.WEST);
        add(parcelPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(summaryPanel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addFindParcelButton(JPanel buttonPanel) {
        JButton findParcelButton = new JButton("Find Parcel");
        findParcelButton.addActionListener(e -> {
            String parcelId = JOptionPane.showInputDialog(this, "Enter Parcel ID:");
            if (parcelId != null) {
                Parcel parcel = manager.findParcelById(parcelId);
                if (parcel != null) {
                    JOptionPane.showMessageDialog(this, "Parcel Details:\n" + parcel);
                } else {
                    JOptionPane.showMessageDialog(this, "Parcel not found!");
                }
            }
        });
        buttonPanel.add(findParcelButton);
    }

    private void initializeCustomerTable(List<Customer> customers) {
        String[] columns = {"Seq No", "Name", "Parcel ID"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Customer customer : customers) {
            model.addRow(new Object[]{
                    customer.getSequenceNumber(),
                    customer.getName(),
                    customer.getParcelId()
            });
        }

        if (customerTable == null) {
            customerTable = new JTable(model);
        } else {
            customerTable.setModel(model);
        }
    }

    private void initializeParcelTable(List<Parcel> parcels) {
        String[] columns = {"Parcel ID", "Dimensions", "Weight", "Days in Depot", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Parcel parcel : parcels) {
            model.addRow(new Object[]{
                    parcel.getParcelId(),
                    parcel.getLength() + " x " + parcel.getWidth() + " x " + parcel.getHeight(),
                    parcel.getWeight(),
                    parcel.getDaysInDepot(),
                    parcel.getStatus()
            });
        }

        if (parcelTable == null) {
            parcelTable = new JTable(model);
        } else {
            parcelTable.setModel(model);
        }

        // Apply color-coded renderer
        parcelTable.getColumnModel().getColumn(4).setCellRenderer(new StatusColorRenderer());
    }

    private void addProcessCustomerButton(JPanel buttonPanel) {
        JButton processButton = new JButton("Process Customer");
        processButton.addActionListener(e -> {
            String message = manager.processNextCustomer();
            JOptionPane.showMessageDialog(this, message);

            // Update tables and summary after processing
            updateCustomerTable();
            updateParcelTable();
            updateSummary();
        });
        buttonPanel.add(processButton);
    }

    private void addGenerateReportButton(JPanel buttonPanel) {
        JButton reportButton = new JButton("Generate Report");
        reportButton.addActionListener(e -> {
            String reportFilename = "resources/Generatedreport.txt";
            manager.saveReport(reportFilename);
            JOptionPane.showMessageDialog(this, "Report saved to " + reportFilename);
        });
        buttonPanel.add(reportButton);
    }

    private void addAddCustomerButton(JPanel buttonPanel) {
        JButton addCustomerButton = new JButton("Add Customer");
        addCustomerButton.addActionListener(e -> {
            try {
                String name = JOptionPane.showInputDialog(this, "Enter Customer Name:");
                String parcelId = JOptionPane.showInputDialog(this, "Enter Parcel ID:");

                if (name == null || name.trim().isEmpty()) {
                    throw new IllegalArgumentException("Customer name cannot be empty.");
                }
                if (parcelId == null || parcelId.trim().isEmpty()) {
                    throw new IllegalArgumentException("Parcel ID cannot be empty.");
                }

                Customer newCustomer = new Customer(manager.getQueue().size() + 1, name.trim(), parcelId.trim());
                manager.addCustomer(newCustomer);
                JOptionPane.showMessageDialog(this, "Customer added successfully!");
                updateCustomerTable();
                updateSummary();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred while adding the customer. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(addCustomerButton);
    }

    private void addAddParcelButton(JPanel buttonPanel) {
        JButton addParcelButton = new JButton("Add Parcel");
        addParcelButton.addActionListener(e -> {
            try {
                String parcelId = JOptionPane.showInputDialog(this, "Enter Parcel ID:");
                String dimensions = JOptionPane.showInputDialog(this, "Enter Dimensions (LxWxH):");
                String weightStr = JOptionPane.showInputDialog(this, "Enter Weight:");
                String daysInDepotStr = JOptionPane.showInputDialog(this, "Enter Days in Depot:");

                if (parcelId != null && dimensions != null && weightStr != null && daysInDepotStr != null) {
                    String[] dims = dimensions.split("x");
                    double length = Double.parseDouble(dims[0].trim());
                    double width = Double.parseDouble(dims[1].trim());
                    double height = Double.parseDouble(dims[2].trim());
                    double weight = Double.parseDouble(weightStr.trim());
                    int daysInDepot = Integer.parseInt(daysInDepotStr.trim());

                    Parcel parcel = new Parcel(parcelId, daysInDepot, weight, length, width, height);
                    manager.addParcel(parcel);
                    JOptionPane.showMessageDialog(this, "Parcel added successfully!");
                    updateParcelTable();
                    updateSummary();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input format. Please try again.");
            }
        });
        buttonPanel.add(addParcelButton);
    }

    private void updateCustomerTable() {
        List<Customer> customers = manager.getAllCustomers();
        initializeCustomerTable(customers);

        // Reapply alternating row renderer
        customerTable.setDefaultRenderer(Object.class, new AlternatingRowRenderer());

        revalidate();
        repaint();
    }

    private void updateParcelTable() {
        List<Parcel> parcels = manager.getAllParcels();
        initializeParcelTable(parcels);

        // Reapply alternating row renderer
        parcelTable.setDefaultRenderer(Object.class, new AlternatingRowRenderer());

        revalidate();
        repaint();
    }


    private void updateSummary() {
        int totalCustomersProcessed = manager.getQueue().getProcessedCount();
        int totalParcelsCollected = manager.getParcelMap().getCollectedCount();
        double totalFeesCollected = manager.getLog().getTotalFees();

        summaryLabel.setText(String.format(
                "<html><b>Total Customers Processed:</b> %d<br>" +
                        "<b>Total Parcels Collected:</b> %d<br>" +
                        "<b>Total Fees Collected:</b> £%.2f<br></html>",
                totalCustomersProcessed, totalParcelsCollected, totalFeesCollected
        ));
    }

    private static class AlternatingRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
                component.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
            } else {
                component.setBackground(Color.BLUE); // Keep selection visible
            }
            return component;
        }
    }

    // Custom Renderer for Status Column
    private static class StatusColorRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            super.setValue(value);
            if (value != null) {
                String status = value.toString();
                if ("collected".equalsIgnoreCase(status)) {
                    setBackground(Color.GREEN);
                    setForeground(Color.BLACK);
                } else if ("waiting".equalsIgnoreCase(status)) {
                    setBackground(Color.RED);
                    setForeground(Color.WHITE);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }
            }
        }
    }
}
