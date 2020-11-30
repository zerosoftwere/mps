package org.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;

@Entity
@Getter
public class Vendor extends PanacheEntity {
    @Column(nullable = false)
    public String name;

    @OneToMany(fetch = FetchType.EAGER)
    List<Service> services;
}
