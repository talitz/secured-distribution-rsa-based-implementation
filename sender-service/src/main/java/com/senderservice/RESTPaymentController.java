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

    public RestTemplate restTemplate = new RestTemplate();
    public HttpHeaders headers = new HttpHeaders();

    @PostMapping(value = "/send-file", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendFile(@RequestParam("fileAsString") String fileAsString) throws URISyntaxException, MalformedURLException {
        logger.info("sendFile() was called");

        //Get the signature for the file
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<String>(fileAsString,headers);
        String fileSignature = restTemplate.postForObject("http://localhost:8080/distributer-service/signature", entity, String.class);
        Message message = new Message(fileAsString,fileSignature);

        //Send the file with signature to the receiver
        Message response = restTemplate.postForObject("http://localhost:8080/receiver-service/receive-file", message, Message.class);

        logger.info("sendFile() ended successfully");
        return new ResponseEntity<>("File was sent with the signature!", HttpStatus.OK);
    }
}