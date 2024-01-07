package ir.rahgozin.branch;

import ir.rahgozin.branch.exception.ErrorRecord;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class BaseTest {
    public static final String LOCAL_HOST_URI = "http://localhost:";

    public <R, T> T postFor200OK(RestTemplate rest, String url, String token, R requestBody, Class<T> responseType) {
        HttpEntity<R> requestEntity = new HttpEntity<>(requestBody, getHeaders(token));
        ResponseEntity<T> response = rest.exchange(url, HttpMethod.POST, requestEntity, responseType);

        if (response.getStatusCode().value() >= 400) {
            log.error(response.getBody());
        }
        return response.getBody();
    }

    public <R, T> T postForErrorResponse(RestTemplate rest, String url, String token, R requestBody) {
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
