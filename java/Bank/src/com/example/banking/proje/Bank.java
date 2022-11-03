package com.example.banking.proje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Bank implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
