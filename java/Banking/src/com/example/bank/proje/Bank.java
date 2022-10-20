package com.example.bank.proje;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Bank {
    private final int id;
    private String name;
    private List<Customer> customers;


    public Bank(int id, String name) {
        this.id = id;
        this.name = name;
        customers = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer createCustomer(String identity, String fullname) {
        Customer customer = new Customer(identity, fullname);
        customers.add(customer);

        return customer;
    }

    public Optional<Customer> getCustomer(String identityNo) {
        for (Customer cust : customers) {
            if(cust.getIdentityNo().equals(identityNo)) {
                return Optional.of(cust);
            } 
            
        }
        return Optional.empty();
    }
}
