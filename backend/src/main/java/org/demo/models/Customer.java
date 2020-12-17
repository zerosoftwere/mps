package org.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Customer extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
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
