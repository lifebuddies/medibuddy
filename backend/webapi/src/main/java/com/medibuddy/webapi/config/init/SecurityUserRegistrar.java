package com.medibuddy.webapi.config.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class SecurityUserRegistrar implements ApplicationRunner {

    @Autowired
    private UserDetailsManager users;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${DEFAULT_SECURITY_USERNAME:username}")
    private String DEFAULT_SECURITY_USERNAME;

    @Value("${DEFAULT_SECURITY_PASSWORD:password}")
    private String DEFAULT_SECURITY_PASSWORD;

    @Value("${DEFAULT_SECURITY_ROLE:USER}")
    private String DEFAULT_SECURITY_ROLE;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        var user = User.withUsername(DEFAULT_SECURITY_USERNAME)
                .password(passwordEncoder.encode(DEFAULT_SECURITY_PASSWORD))
                .roles(DEFAULT_SECURITY_ROLE)
                .build();
        if (!users.userExists(DEFAULT_SECURITY_USERNAME)) {
            users.createUser(user);
        }
    }

}
