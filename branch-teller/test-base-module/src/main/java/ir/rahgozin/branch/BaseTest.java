package ir.rahgozin.branch;

import ir.rahgozin.branch.exception.ErrorRecord;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class BaseTest {
    public static final String LOCAL_HOST_URI = "http://localhost:";

    public <R, T> T postFor_200_OK(RestTemplate rest, String url, String token, R requestBody, Class<T> responseType) {
        HttpEntity<R> requestEntity = new HttpEntity<>(requestBody, getHeaders(token));
        ResponseEntity<T> responseEntity = rest.postForEntity(url, requestEntity, responseType);
        return responseEntity.getBody();
    }

    public <R, T> T postFor_ErrorResponse(RestTemplate rest, String url, String token, R requestBody, Class<T> responseType) {
        HttpEntity<R> requestEntity = new HttpEntity<>(requestBody, getHeaders(token));
        ResponseEntity<ErrorRecord> response = rest.exchange(url, HttpMethod.POST, requestEntity, ErrorRecord.class);

        return (T) response.getBody();
    }

    private HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "bearer " + token);
        return headers;
    }
}
