package org.demo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Transaction extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(optional = false)
    public User user;

    public String service;
    public String vendor;
    public int amount;
    public Status status;
    public String email;
    public String phone;
    public String customer;

    @CreationTimestamp
    public Date dateCreated;
    
    @UpdateTimestamp
    public Date dateUpdated;

    public static enum Status {
        SUCCESSFULL, FAILED
    }
}
