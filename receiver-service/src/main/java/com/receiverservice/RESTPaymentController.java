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

    @PostMapping(value = "/receive-file", produces={"application/json"})
    public ResponseEntity<?> receiveFile(@RequestBody Message message) throws URISyntaxException {
        logger.info("receiveFile() was called");

        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("receiveFile() - get the public key from the distributer service");
        KEY publicKey = restTemplate.getForObject("http://localhost:8080/distributer-service/public-key", KEY.class);

        int hashed = message.getFileAsString().hashCode();

        logger.info("receiveFile() - decrypting using RSA decrypt function");
        String decryptedSig = RSA.decrypt(message.getSignature(), publicKey);

        logger.info("receiveFile() - decryptedSig = "+decryptedSig);
        logger.info("receiveFile() - original hashed string = "+hashed);
        
        
        Boolean isValid = Integer.toString(hashed).equalsIgnoreCase(decryptedSig);

        logger.info("receiveFile() ended successfully");
        if(isValid) {
            logger.info("receiveFile() the signature is valid!");
            return new ResponseEntity<>("The file is valid!", HttpStatus.OK);
        } else {
            logger.info("receiveFile() the signature is NOT valid!");
            return new ResponseEntity<>("The file is NOT valid!", HttpStatus.BAD_REQUEST);
        }
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
