package com.example.senderservice.service.impl;

import com.example.senderservice.service.RestService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestServiceImpl implements RestService {
    final RestTemplate customRestTemplate;

    @Override
    public JsonNode getUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getUser";
        return customRestTemplate.postForObject(url, request, JsonNode.class);
    }

}
