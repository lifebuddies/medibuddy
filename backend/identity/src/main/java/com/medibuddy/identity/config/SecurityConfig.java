package com.medibuddy.identity.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
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

	@Value("${ISSUER_URI:http://localhost:9000}")
	private String ISSUER_URI;

	@Value("${MOBILE_CLIENT_SCOPE:mobile.full_access}")
	private String MOBILE_CLIENT_SCOPE;

	@Value("${FRONTEND_CLIENT_SCOPE:frontend.full_access}")
	private String FRONTEND_CLIENT_SCOPE;

	@Value("${WEBAPI_CLIENT_SCOPE:webapi.full_access}")
	private String WEBAPI_CLIENT_SCOPE;

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
		return new JdbcRegisteredClientRepository(template);
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
		return new JdbcUserDetailsManager(dataSource);
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
