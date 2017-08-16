package com.gachaland.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestClient {

//    @Autowired
//    private RestTemplate restTemplate;

    private HttpHeaders httpHeaders;

    public String getRequest(String url) {
        HttpEntity<String> httpEntity = makeHttpEntity(this.httpHeaders, null);

        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = createGetRequest(url, httpEntity, String.class);
        } catch (RestClientException ex) {
            ex.printStackTrace();
            return null;
        }
        return responseEntity.getBody();
    }

    private HttpEntity<String> makeHttpEntity(HttpHeaders httpHeaders, String parameters) {
        if (httpHeaders == null)
            httpHeaders = this.httpHeaders;

        return new HttpEntity<>(parameters, httpHeaders);
    }

    private <T> ResponseEntity<T> createGetRequest(String url, HttpEntity httpEntity, Class<T> tclass) throws RestClientException {
        return new RestTemplate().exchange(url, HttpMethod.GET, httpEntity, tclass);
    }

}
