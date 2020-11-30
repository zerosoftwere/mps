package org.demo.models;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Transaction extends PanacheEntity {
    public String service;
    public String vendor;
    public int amount;
    public Status status;
    public Long userId;
    public String email;
    public String phone;
    public String customer;

    @CreationTimestamp
    public Date dataCreated;
    
    @UpdateTimestamp
    public Date dataUpdated;

    public static enum Status {
        SUCCESSFULL, FAILED
    }
}
