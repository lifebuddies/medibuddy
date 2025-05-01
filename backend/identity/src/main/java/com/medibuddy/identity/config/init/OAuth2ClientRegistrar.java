package com.medibuddy.identity.config.init;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

@Component
public class OAuth2ClientRegistrar implements ApplicationRunner {

    @Autowired
    private RegisteredClientRepository clients;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${FRONTEND_CLIENT_ID:medibuddy-frontend}")
    private String FRONTEND_CLIENT_ID;

    @Value("${FRONTEND_REDIRECT_URI:http://localhost:3000/callback}")
    private String FRONTEND_REDIRECT_URI;

    @Value("${FRONTEND_POST_LOGOUT_REDIRECT_URI:http://localhost:3000}")
    private String FRONTEND_POST_LOGOUT_REDIRECT_URI;

    @Value("${MOBILE_CLIENT_ID:medibuddy-mobile-app}")
    private String MOBILE_CLIENT_ID;

    @Value("${MOBILE_REDIRECT_URI:com.medibuddy.mobile://login-callback}")
    private String MOBILE_REDIRECT_URI;

    @Value("${MOBILE_POST_LOGOUT_REDIRECT_URI:com.medibuddy.mobile://logout-callback}")
    private String MOBILE_POST_LOGOUT_REDIRECT_URI;

    @Value("${WEBAPI_CLIENT_ID:medibuddy-webapi}")
    private String WEBAPI_CLIENT_ID;

    @Value("${WEBAPI_CLIENT_SECRET:medibuddy-webapi-secret}")
    private String WEBAPI_CLIENT_SECRET;

    @Value("${WEBAPI_REDIRECT_URI:http://localhost:8080/login/oauth2/code/medibuddy-webapi}")
    private String WEBAPI_REDIRECT_URI;

    @Value("${FRONTEND_CLIENT_SCOPE:frontend.full_access}")
    private String FRONTEND_CLIENT_SCOPE;

    @Value("${MOBILE_CLIENT_SCOPE:mobile.full_access}")
    private String MOBILE_CLIENT_SCOPE;

    @Value("${WEBAPI_CLIENT_SCOPE:webapi.full_access}")
    private String WEBAPI_CLIENT_SCOPE;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var frontendClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(FRONTEND_CLIENT_ID)
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri(FRONTEND_REDIRECT_URI)
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope(OidcScopes.EMAIL)
                .scopes((scopes) -> scopes.addAll(List.of(FRONTEND_CLIENT_SCOPE.split(" "))))
                .redirectUri(FRONTEND_REDIRECT_URI)
                .postLogoutRedirectUri(FRONTEND_POST_LOGOUT_REDIRECT_URI)
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .requireProofKey(true)
                        .build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                        .accessTokenTimeToLive(Duration.ofMinutes(15))
                        .refreshTokenTimeToLive(Duration.ofDays(7))
                        .build())
                .build();

        var mobileClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(MOBILE_CLIENT_ID)
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope(OidcScopes.EMAIL)
                .scopes((scopes) -> scopes.addAll(List.of(MOBILE_CLIENT_SCOPE.split(" "))))
                .redirectUri(MOBILE_REDIRECT_URI)
                .postLogoutRedirectUri(MOBILE_POST_LOGOUT_REDIRECT_URI)
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .requireProofKey(true)
                        .build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                        .accessTokenTimeToLive(Duration.ofMinutes(15))
                        .refreshTokenTimeToLive(Duration.ofDays(7))
                        .build())
                .build();

        var webApiClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(WEBAPI_CLIENT_ID)
                .clientSecret(passwordEncoder.encode(WEBAPI_CLIENT_SECRET))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scopes((scopes) -> scopes.addAll(List.of(WEBAPI_CLIENT_SCOPE.split(" "))))
                .redirectUri(WEBAPI_REDIRECT_URI)
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                        .accessTokenTimeToLive(Duration.ofMinutes(15))
                        .refreshTokenTimeToLive(Duration.ofDays(7))
                        .build())
                .build();

        if (clients.findByClientId(FRONTEND_CLIENT_ID) == null)
            clients.save(frontendClient);
        if (clients.findByClientId(MOBILE_CLIENT_ID) == null)
            clients.save(mobileClient);
        if (clients.findByClientId(WEBAPI_CLIENT_ID) == null)
            clients.save(webApiClient);
    }

}
