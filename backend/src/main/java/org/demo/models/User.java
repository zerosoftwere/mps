package org.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class User extends PanacheEntity {
    @Column(unique = true)
    public String email;

    @JsonIgnore
    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public String role;

    public static User findByEmail(String email) {
        return User.find("email", email).firstResult();
    }
}
