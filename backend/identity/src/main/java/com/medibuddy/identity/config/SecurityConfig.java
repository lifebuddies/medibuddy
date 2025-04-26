package com.medibuddy.identity.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${REDIRECT_URI:http://localhost:4200/callback}")
	private String REDIRECT_URI;

	@Value("${ISSUER_URI:http://localhost:9000}")
	private String ISSUER_URI;

	@Value("${FRONTEND_CLIENT_ID:medibuddy-frontend}")
	private String FRONTEND_CLIENT_ID;

	@Value("${MOBILE_APP_CLIENT_ID:medibuddy-mobile-app}")
	private String MOBILE_APP_CLIENT_ID;

	@Value("${WEB_API_CLIENT_ID:medibuddy-webapi}")
	private String WEB_API_CLIENT_ID;

	@Value("${WEB_API_CLIENT_SECRET:medibuddy-webapi-secret}")
	private String WEB_API_CLIENT_SECRET;

	private Set<String> MOBILE_APP_CLIENT_SCOPES = Set.of();
	private Set<String> FRONTEND_CLIENT_SCOPES = Set.of();
	private Set<String> WEB_API_CLIENT_SCOPES = Set.of();

	@Value("${DEFAULT_SECURITY_USERNAME:username}")
	private String DEFAULT_SECURITY_USERNAME;

	@Value("${DEFAULT_SECURITY_PASSWORD:password}")
	private String DEFAULT_SECURITY_PASSWORD;

	@Value("${DEFAULT_SECURITY_ROLE:USER}")
	private String DEFAULT_SECURITY_ROLE;

	@Bean
	@Order(1)
	SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
			throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
				.oidc(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	@Order(2)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.oauth2Login(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder()
				.issuer(ISSUER_URI)
				.build();
	}

	@Bean
	RegisteredClientRepository registeredClientRepository(JdbcTemplate template) {
		var clients = new JdbcRegisteredClientRepository(template);

		var frontendClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId(FRONTEND_CLIENT_ID)
				.redirectUri(REDIRECT_URI)
				.scope(OidcScopes.OPENID)
				.scope(OidcScopes.PROFILE)
				.scope(OidcScopes.EMAIL)
				.scopes((scopes) -> scopes.addAll(FRONTEND_CLIENT_SCOPES))
				.clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
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

		var mobileAppClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId(MOBILE_APP_CLIENT_ID)
				.clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.scope(OidcScopes.OPENID)
				.scope(OidcScopes.PROFILE)
				.scope(OidcScopes.EMAIL)
				.scopes((scopes) -> scopes.addAll(MOBILE_APP_CLIENT_SCOPES))
				.redirectUri(REDIRECT_URI)
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
				.clientId(WEB_API_CLIENT_ID)
				.clientSecret(WEB_API_CLIENT_SECRET)
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scopes((scopes) -> scopes.addAll(WEB_API_CLIENT_SCOPES))
				.redirectUri(REDIRECT_URI)
				.tokenSettings(TokenSettings.builder()
						.accessTokenFormat(OAuth2TokenFormat.REFERENCE)
						.accessTokenTimeToLive(Duration.ofMinutes(15))
						.refreshTokenTimeToLive(Duration.ofDays(7))
						.build())
				.build();

		if (clients.findByClientId(FRONTEND_CLIENT_ID) == null)
			clients.save(frontendClient);
		if (clients.findByClientId(MOBILE_APP_CLIENT_ID) == null)
			clients.save(mobileAppClient);
		if (clients.findByClientId(WEB_API_CLIENT_ID) == null)
			clients.save(webApiClient);

		return clients;
	}

	@Bean
	JWKSource<SecurityContext> jwkSource(
			@Value("${spring.security.oauth2.authorizationserver.jwt.key-id}") String keyID,
			@Value("${spring.security.oauth2.authorizationserver.jwt.private-key-location}") RSAPrivateKey privateKey,
			@Value("${spring.security.oauth2.authorizationserver.jwt.public-key-location}") RSAPublicKey publicKey) {
		RSAKey rsaKey = new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
				.keyID(keyID)
				.build();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	@Bean
	JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}

	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		var users = new JdbcUserDetailsManager(dataSource);
		var user = User.withUsername(DEFAULT_SECURITY_USERNAME)
				.password(passwordEncoder().encode(DEFAULT_SECURITY_PASSWORD))
				.roles(DEFAULT_SECURITY_ROLE)
				.build();
		if (!users.userExists(DEFAULT_SECURITY_USERNAME)) {
			users.createUser(user);
		}
		return users;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Map<String, PasswordEncoder> encoders = Map.of("bcrypt", bcrypt);
		return new DelegatingPasswordEncoder("bcrypt", encoders);
	}

	@Bean
	OAuth2AuthorizationConsentService consentService(JdbcTemplate template,
			RegisteredClientRepository clientRepository) {
		return new JdbcOAuth2AuthorizationConsentService(template, clientRepository);
	}

	@Bean
	OAuth2AuthorizationService authorizationService(JdbcTemplate template,
			RegisteredClientRepository clientRepository) {
		return new JdbcOAuth2AuthorizationService(template, clientRepository);
	}

}
