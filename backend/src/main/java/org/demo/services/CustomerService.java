package org.demo.services;

import javax.enterprise.context.ApplicationScoped;

import org.demo.models.Customer;
import org.demo.models.User;

@ApplicationScoped
public class CustomerService {
    public Customer create(User user, String name, String phone) {
        Customer customer = new Customer();
        customer.name = name;
        customer.user  = user;
        customer.phone = phone;
        customer.persist();
        
        return customer;
    }
}
