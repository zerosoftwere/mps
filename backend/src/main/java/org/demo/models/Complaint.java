package org.demo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Complaint extends PanacheEntity {
    @ManyToOne(optional = false)
    public Customer customer;

    @Column(nullable = false)
    public String message;

    public Status status = Status.OPEN;

    @CreationTimestamp
    public Date dateRaised;

    @UpdateTimestamp
    public Date dateResolved;
    
    @ManyToOne(optional = false)
    public User resolvedBy;

    @Column(nullable = false)
    public String resolution;

    public static List<Complaint> findByUser(User user) {
        return Complaint.find("customer.user", user).list();
    }

    public static enum Status {
        OPEN, CLOSED
    }
}
