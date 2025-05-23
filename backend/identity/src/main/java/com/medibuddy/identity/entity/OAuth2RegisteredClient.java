package com.medibuddy.identity.entity;

import jakarta.persistence.*;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "oauth2_registered_client")
@EntityListeners(AuditingEntityListener.class)
public class OAuth2RegisteredClient {

    @Id
    @Column(length = 100, nullable = false)
    private String id;

    @Column(name = "client_id", length = 100, nullable = false)
    private String clientId;

    @CreatedDate
    @Column(name = "client_id_issued_at", nullable = false)
    private Instant clientIdIssuedAt;

    @Column(name = "client_secret", length = 200)
    private String clientSecret;

    @Column(name = "client_secret_expires_at")
    private Instant clientSecretExpiresAt;

    @Column(name = "client_name", length = 200, nullable = false)
    private String clientName;

    @Column(name = "client_authentication_methods", length = 1000, nullable = false)
    private String clientAuthenticationMethods;

    @Column(name = "authorization_grant_types", length = 1000, nullable = false)
    private String authorizationGrantTypes;

    @Column(name = "redirect_uris", length = 1000)
    private String redirectUris;

    @Column(name = "post_logout_redirect_uris", length = 1000)
    private String postLogoutRedirectUris;

    @Column(name = "scopes", length = 1000, nullable = false)
    private String scopes;

    @Column(name = "client_settings", length = 2000, nullable = false)
    private String clientSettings;

    @Column(name = "token_settings", length = 2000, nullable = false)
    private String tokenSettings;

}
