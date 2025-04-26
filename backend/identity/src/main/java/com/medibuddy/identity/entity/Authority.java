package com.medibuddy.identity.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "authorities", indexes = @Index(name = "ix_auth_username", columnList = "username, authority", unique = true))
@IdClass(Authority.UserAuthorityId.class)
public class Authority {

    public record UserAuthorityId(User user, String authority) implements Serializable {
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Id
    @Column(name = "authority", length = 50, nullable = false)
    private String authority;

}
