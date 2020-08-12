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

        
        logger.info("sendFile() fileAsString = " + fileAsString);


        logger.info("sendFile() - sending signature POST request to distributer service");
        //String fileSignature = restTemplate.postForObject("http://localhost:8080/distributer-service/signature", entity, String.class);
        String fileSignature = restTemplate.postForObject("http://localhost:8080/distributer-service/signature", fileAsString, String.class);

        Message message = new Message(fileAsString,fileSignature);

        logger.info("sendFile() - sending the file with it's signature to the receiver service");
        String response = restTemplate.postForObject("http://localhost:8081/receiver-service/receive-file", message, String.class);

        logger.info("sendFile() ended successfully");
        
        //TODO: print the response
        return new ResponseEntity<>("File was sent with the signature!, response = " + response + "\n", HttpStatus.OK);
    }
    
    @PostMapping(value = "/send-file-corrupted", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendFileCorrupted(@RequestParam("fileAsString") String fileAsString) throws URISyntaxException, MalformedURLException {
        logger.info("sendFileCorrupted() was called");
        //Get the signature for the file
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        logger.info("sendFileCorrupted() fileAsString = " + fileAsString);
        logger.info("sendFileCorrupted() - sending signature POST request to distributer service");
        String fileSignature = restTemplate.postForObject("http://localhost:8080/distributer-service/signature", fileAsString, String.class);
        
        //modify slightly the file after generating the file signature 
        String fileAsStringChanged = fileAsString + " ";
        
        Message message = new Message(fileAsStringChanged,fileSignature);

        logger.info("sendFileCorrupted() - sending the file with it's signature to the receiver service");
        String response = restTemplate.postForObject("http://localhost:8081/receiver-service/receive-file", message, String.class);

        logger.info("sendFileCorrupted() ended successfully");
        
        //TODO: print the response
        return new ResponseEntity<>("File was sent with the signature!, response = " + response + "\n", HttpStatus.OK);
    }
}