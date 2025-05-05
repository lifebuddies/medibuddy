package com.medibuddy.webapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
public class SecurityConfig {

    @Value("${MOBILE_CLIENT_SCOPE:mobile.full_access}")
    private String MOBILE_CLIENT_SCOPE;

    @Value("${FRONTEND_CLIENT_SCOPE:frontend.full_access}")
    private String FRONTEND_CLIENT_SCOPE;

    @Bean
    RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withRolePrefix("SCOPE_")
                .role(FRONTEND_CLIENT_SCOPE).implies("feedback.write", "newsletter.subscribe")
                .role(MOBILE_CLIENT_SCOPE).implies("record.write", "record.read", "analysis.write", "analysis.read")
                .build();
    }

    @Bean
    MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);
        return handler;
    }

    @Bean
    SecurityExpressionHandler<FilterInvocation> webSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setDefaultRolePrefix("");
        handler.setRoleHierarchy(roleHierarchy);
        return handler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/api/**").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(Customizer.withDefaults()));
        return http.build();
    }

}
