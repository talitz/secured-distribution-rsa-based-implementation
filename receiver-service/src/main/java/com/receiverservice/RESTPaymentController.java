package com.receiverservice;

import com.google.gson.Gson;
import com.rsa.KEY;
import com.rsa.RSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.UUID;
import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/receiver-service")
public class RESTPaymentController {

    @Autowired
    public LoggingController logger;

    @Autowired
    private Gson gson;

    public RestTemplate restTemplate = new RestTemplate();
    public HttpHeaders headers = new HttpHeaders();

    @GetMapping(value = "/receive-file", produces={"application/json"})
    public ResponseEntity<String> receiveFile(Message message) throws URISyntaxException {
        logger.info("receiveFile() was called");

        headers.setContentType(MediaType.APPLICATION_JSON);
        KEY publicKey = restTemplate.getForObject("http://localhost:8080/distributer-service/public-key", KEY.class);

        int hashed = message.getFileAsString().hashCode();

        String decryptedSig = RSA.decrypt(message.getSignature(), publicKey);

        Boolean isValid = Integer.toString(hashed).equalsIgnoreCase(decryptedSig);

        logger.info("receiveFile() ended successfully");
        if(isValid) {
            return new ResponseEntity<>("The file is valid!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The file is NOT valid!", HttpStatus.FORBIDDEN);
        }
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
