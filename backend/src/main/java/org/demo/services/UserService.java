package org.demo.services;

import javax.enterprise.context.ApplicationScoped;

import org.demo.models.User;

@ApplicationScoped
public class UserService {
    public User create(String email, String password, String role) {
        User user = new User();
        user.email = email;
        user.role = role;
        user.password = password; // this should be hashed
        user.persist();
        
        return user;
    }
}
