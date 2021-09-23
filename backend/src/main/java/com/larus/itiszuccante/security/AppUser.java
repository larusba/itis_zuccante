package com.larus.itiszuccante.security;

import org.springframework.security.core.userdetails.User;

public class AppUser extends User {

    private String id;

    public AppUser(User user, String id) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.id = id;
    }
}
