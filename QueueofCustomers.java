package service;

import model.Customer;
import java.util.LinkedList;
import java.util.Queue;

public class QueueofCustomers {
    private Queue<Customer> customers = new LinkedList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer removeCustomer() {
        return customers.poll();
    }

    public int getQueueSize() {
        return customers.size();
    }
}
