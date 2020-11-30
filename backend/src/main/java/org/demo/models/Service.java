package org.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Service extends PanacheEntity {

    @Column(nullable = false)
    public String name;
    
    public Boolean fixed;
    public Integer amount;

    @ManyToOne
    @JsonIgnore
    public Vendor vendor;
}
