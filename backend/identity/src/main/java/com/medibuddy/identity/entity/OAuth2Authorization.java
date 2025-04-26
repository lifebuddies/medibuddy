package com.medibuddy.identity.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "oauth2_authorization")
public class OAuth2Authorization {

    @Id
    @Column(length = 100, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "registered_client_id", nullable = false)
    private OAuth2RegisteredClient registeredClient;

    @Column(name = "principal_name", length = 200, nullable = false)
    private String principalName;

    @Column(name = "authorization_grant_type", length = 100, nullable = false)
    private String authorizationGrantType;

    @Column(name = "authorized_scopes", length = 1000)
    private String authorizedScopes;

    @Lob
    @Column(name = "attributes", columnDefinition = "TEXT")
    private String attributes;

    @Column(name = "state", length = 500)
    private String state;

    @Lob
    @Column(name = "authorization_code_value", columnDefinition = "TEXT")
    private String authorizationCodeValue;

    @Column(name = "authorization_code_issued_at")
    private Instant authorizationCodeIssuedAt;

    @Column(name = "authorization_code_expires_at")
    private Instant authorizationCodeExpiresAt;

    @Lob
    @Column(name = "authorization_code_metadata", columnDefinition = "TEXT")
    private String authorizationCodeMetadata;

    @Lob
    @Column(name = "access_token_value", columnDefinition = "TEXT")
    private String accessTokenValue;

    @Column(name = "access_token_issued_at")
    private Instant accessTokenIssuedAt;

    @Column(name = "access_token_expires_at")
    private Instant accessTokenExpiresAt;

    @Lob
    @Column(name = "access_token_metadata", columnDefinition = "TEXT")
    private String accessTokenMetadata;

    @Column(name = "access_token_type", length = 100)
    private String accessTokenType;

    @Column(name = "access_token_scopes", length = 1000)
    private String accessTokenScopes;

    @Lob
    @Column(name = "oidc_id_token_value", columnDefinition = "TEXT")
    private String oidcIdTokenValue;

    @Column(name = "oidc_id_token_issued_at")
    private Instant oidcIdTokenIssuedAt;

    @Column(name = "oidc_id_token_expires_at")
    private Instant oidcIdTokenExpiresAt;

    @Lob
    @Column(name = "oidc_id_token_metadata", columnDefinition = "TEXT")
    private String oidcIdTokenMetadata;

    @Lob
    @Column(name = "refresh_token_value", columnDefinition = "TEXT")
    private String refreshTokenValue;

    @Column(name = "refresh_token_issued_at")
    private Instant refreshTokenIssuedAt;

    @Column(name = "refresh_token_expires_at")
    private Instant refreshTokenExpiresAt;

    @Lob
    @Column(name = "refresh_token_metadata", columnDefinition = "TEXT")
    private String refreshTokenMetadata;

    @Lob
    @Column(name = "user_code_value", columnDefinition = "TEXT")
    private String userCodeValue;

    @Column(name = "user_code_issued_at")
    private Instant userCodeIssuedAt;

    @Column(name = "user_code_expires_at")
    private Instant userCodeExpiresAt;

    @Lob
    @Column(name = "user_code_metadata", columnDefinition = "TEXT")
    private String userCodeMetadata;

    @Lob
    @Column(name = "device_code_value", columnDefinition = "TEXT")
    private String deviceCodeValue;

    @Column(name = "device_code_issued_at")
    private Instant deviceCodeIssuedAt;

    @Column(name = "device_code_expires_at")
    private Instant deviceCodeExpiresAt;

    @Lob
    @Column(name = "device_code_metadata", columnDefinition = "TEXT")
    private String deviceCodeMetadata;
}

