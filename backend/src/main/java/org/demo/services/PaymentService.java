package org.demo.services;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import org.demo.domain.CheckoutSummary;
import org.demo.models.Customer;
import org.demo.models.Service;
import org.demo.models.Transaction;
import org.demo.models.User;

@ApplicationScoped
public class PaymentService {
    
    public CheckoutSummary intiate(long id, int quantity, int amount) {
        Service service = Service.findById(id);
        if (service == null) throw new NotFoundException();

        int total = amount;
        if (service.fixed)
            total = quantity * service.amount;
        
        CheckoutSummary summary = new CheckoutSummary();
        summary.setAmount(total);
        summary.setName(service.name);
        summary.setVendor(service.vendor.name);

        return summary;
    }

    public boolean execute(User user, long id, int quantity, int amount) {
        CheckoutSummary summary = intiate(id, quantity, amount);
        Customer customer = Customer.findByUser(user);
        Service service = Service.findById(id);
        
        Transaction transaction = new Transaction();
        transaction.amount = summary.getAmount();
        transaction.userId = user.id;
        transaction.service = service.name;
        transaction.status = Transaction.Status.SUCCESSFULL;
        transaction.customer = customer.name;
        transaction.phone = customer.phone;
        transaction.email = user.email;
        transaction.vendor = service.vendor.name;
        transaction.persist();

        return true; // this should communicate with the outside world to perform transaction
    }
}