package org.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Customer extends PanacheEntity {
    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String phone;

    @OneToOne(optional = false)
    public User user;

    public static Customer findByUser(User user) {
        return find("user", user).firstResult();
    }
}
