package org.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

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

    public static boolean exists(String email) {
        return User.find("email", email).count() > 0;
    }
}
