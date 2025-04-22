package com.medibuddy.identity.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users", indexes = @Index(name = "ix_username", columnList = "username", unique = true))
public class User {

	@Id
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 500, nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

}
