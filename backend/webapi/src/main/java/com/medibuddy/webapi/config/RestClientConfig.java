package com.medibuddy.webapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${WEB_API_CLIENT_ID:medibuddy-webapi}")
    private String WEB_API_CLIENT_ID;

    @Bean
    RestClient restClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        return RestClient.builder()
            .requestInterceptor((request, body, execution) -> {
                // Build request to authorize client
                OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId(WEB_API_CLIENT_ID)  // This must match application.yml
                    .principal("system")                   // dummy principal (required)
                    .build();

                // Get access token
                OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
                if (authorizedClient == null) {
                    throw new IllegalStateException("OAuth2 client authorization failed");
                }

                String token = authorizedClient.getAccessToken().getTokenValue();
                request.getHeaders().setBearerAuth(token);
                return execution.execute(request, body);
            })
            .build();
    }
}
