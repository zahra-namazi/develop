package ir.rahgozin.branch.externalservice.iam.service.impl;

import ir.rahgozin.branch.externalservice.iam.service.IamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class IamServiceImpl implements IamService {
    private static final String BEARER_STR = "Bearer ";
    private String cachedToken = null;

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Cacheable(value = "token")
    public String getToken() {
        if (cachedToken != null) return cachedToken;

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakUrl)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();

        AccessTokenResponse accessTokenResponse = keycloak.tokenManager().getAccessToken();
        this.cachedToken = BEARER_STR + accessTokenResponse.getToken();

        return cachedToken;
    }
}
