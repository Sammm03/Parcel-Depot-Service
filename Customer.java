package model;

public class Customer {
    private int sequenceNumber;
    private String name;

    public Customer(int sequenceNumber, String name) {
        this.sequenceNumber = sequenceNumber;
        this.name = name;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public String getName() {
        return name;
    }
}
