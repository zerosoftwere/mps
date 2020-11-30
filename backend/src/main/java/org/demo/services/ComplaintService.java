package org.demo.services;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import org.demo.models.Complaint;
import org.demo.models.Customer;
import org.demo.models.User;

@ApplicationScoped
public class ComplaintService {

    public Complaint create(User user, String message) {
        Customer customer = Customer.findByUser(user);

        Complaint complaint = new Complaint();
        complaint.customer = customer;
        complaint.message = message;
        complaint.persist();
        
        return complaint;
    }

    public void close(Long id, String resolution, User resolver) {
        Complaint complaint = Complaint.findById(id);
        if (complaint == null) throw new NotFoundException();

        complaint.resolution = resolution;
        complaint.resolvedBy = resolver;
        complaint.status = Complaint.Status.CLOSED;
        complaint.persist();
    }
}
