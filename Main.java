package view;

import controller.Manager;
import controller.Worker;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Worker worker = new Worker(manager.getQueue(), manager.getParcelMap());

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(manager, worker);
            mainFrame.setVisible(true);
        });
    }
}
