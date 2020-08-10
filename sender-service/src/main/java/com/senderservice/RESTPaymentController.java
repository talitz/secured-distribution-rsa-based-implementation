package com.senderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/sender-service")
public class RESTPaymentController {

    @Autowired
    public LoggingController logger;

    @PostMapping(value = "/send-file", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendFile(@RequestParam("fileAsString") String fileAsString) throws URISyntaxException, MalformedURLException {
        logger.info("sendFile() was called");
        String response = getResponse("This is the file bro!","http://localhost:8080/distributer-service/signature");
        logger.info("response = "+ response);
        logger.info("sendFile() ended successfully");
        return new ResponseEntity<>("File was sent with the signature!", HttpStatus.OK);
    }

    public String getResponse(String fileAsString, String url) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");

        String responseBody = post(url, fileAsString, headers, String.class);
        return responseBody;
    }

    public <T> T post(String url, Object requestObject, MultiValueMap<String, String> headers, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        HttpEntity request = new HttpEntity(requestObject, headers);
        T responseObject = restTemplate.postForObject(url, request, responseType);
        return responseObject;
    }
}