package model;

import java.util.LinkedList;
import java.util.Queue;

public class QueueofCustomers {
    private Queue<Customer> customers; // Non-static variable

    public QueueofCustomers() {
        customers = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer); // Accessing the instance variable
    }

    public Customer removeCustomer() {
        return customers.poll(); // Removes and returns the head of the queue
    }

    public Customer peekCustomer() {
        return customers.peek(); // Returns the head of the queue without removing
    }

    public Queue<Customer> getCustomers() {
        return customers; // Returns the queue
    }

    public boolean isEmpty() {
        return customers.isEmpty();
    }
}
