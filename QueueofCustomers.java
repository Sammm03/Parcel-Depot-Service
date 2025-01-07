package model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages the queue of customers waiting to collect parcels.
 * Provides functionality to add, remove, and view customers in the queue.
 */
public class QueueofCustomers {
    private final Queue<Customer> customers; // Queue to hold customers
    private int processedCount; // Counter for processed customers

    /**
     * Constructor to initialize the customer queue.
     */
    public QueueofCustomers() {
        this.customers = new LinkedList<>();
        this.processedCount = 0;
    }

    /**
     * Adds a customer to the queue.
     *
     * @param customer The customer to add.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Removes and returns the next customer in the queue.
     *
     * @return The next customer in the queue.
     */
    public Customer removeCustomer() {
        processedCount++;
        return customers.poll(); // Removes the head of the queue
    }

    /**
     * Retrieves the next customer in the queue without removing them.
     *
     * @return The next customer in the queue, or null if the queue is empty.
     */
    public Customer peekCustomer() {
        return customers.peek(); // Returns the head of the queue
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return customers.isEmpty();
    }

    /**
     * Retrieves all customers in the queue.
     *
     * @return A queue of all customers.
     */
    public Queue<Customer> getCustomers() {
        return new LinkedList<>(customers); // Return a copy of the queue
    }

    /**
     * Gets the count of processed customers.
     *
     * @return The number of customers processed so far.
     */
    public int getProcessedCount() {
        return processedCount;
    }

    /**
     * Retrieves the size of the current queue.
     *
     * @return The number of customers currently in the queue.
     */
    public int size() {
        return customers.size();
    }
}
