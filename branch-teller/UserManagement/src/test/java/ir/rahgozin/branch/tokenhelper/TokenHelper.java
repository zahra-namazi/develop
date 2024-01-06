package ir.rahgozin.branch.tokenhelper;

import lombok.Builder;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TokenHelper {

    public static String getTokenForBackOfficeClerkUser(TestRestTemplate rest) {
        TokenData tokenData = TokenData.builder().grantType("password")
                .clientId("branch-channel")
                .clientSecret("branch-channel-secret")
                .username("back-office-clerk@blubank.com")
                .password("password").build();
        return getToken(rest, tokenData);
    }

    private static String getToken(TestRestTemplate rest, TokenData  tokenData) {
        String url = "http://localhost:8080/auth/realms/blubank/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", tokenData.grantType);
        map.add("client_id", tokenData.clientId);
        map.add("client_secret", tokenData.clientSecret);
        map.add("username", tokenData.username);
        map.add("password", tokenData.password);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<TokenResponse> response = rest.exchange(url, HttpMethod.POST, entity, TokenResponse.class);
        return response.getBody().accessToken();
    }

    @Builder
    private  record TokenData(
            String grantType,
            String clientId,
            String clientSecret,
            String username,
            String password
    ) { }
}
