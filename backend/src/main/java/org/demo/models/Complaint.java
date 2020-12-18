package org.demo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "complaints")
public class Complaint extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(optional = false)
    public Customer customer;

    @Column(nullable = false)
    public String message;

    public Status status = Status.OPEN;

    @CreationTimestamp
    public Date dateRaised;

    @UpdateTimestamp
    public Date dateResolved;
    
    @ManyToOne()
    public User resolvedBy;

    @Column()
    public String resolution;

    public static List<Complaint> findByUser(User user) {
        return Complaint.find("customer.user", user).list();
    }

    public static enum Status {
        OPEN, CLOSED
    }
}
