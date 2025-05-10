package com.medibuddy.webapi.entity.account;

import java.time.Instant;

import com.medibuddy.webapi.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_users_username", columnList = "username", unique = true),
        @Index(name = "idx_users_email", columnList = "email", unique = true)
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class User {

    @Id
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 500, nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    // email max length: https://datatracker.ietf.org/doc/html/rfc5321#section-4.5.3.1.3
    @Column(name = "email", length = 256, nullable = true, unique = true)
    private String email;

    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "registration_verified", nullable = true)
    private Boolean registrationVerified;

    @Column(name = "registration_date", nullable = true)
    private Instant registrationDate;

}
