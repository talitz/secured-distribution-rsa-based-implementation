package com.distributerservice;

import com.google.gson.Gson;
import com.rsa.KEY;
import com.rsa.RSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.UUID;
import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/distributer-service")
public class RESTPaymentController {

    @Autowired
    public LoggingController logger;

    private RSA rsa;

    private KEY publicKey, privateKey;

    @PostConstruct
    public void init() {
        logger.info("init() was called");
        rsa = new RSA();
        rsa.generateKeys();
        publicKey = rsa.getPublicKey();
        privateKey = rsa.getPrivateKey();
        logger.info("init() ended successfully");
    }

    @GetMapping(value = "/public-key", produces={"application/json"})
    public ResponseEntity<com.rsa.KEY> getPublicKey() throws URISyntaxException {
        logger.info("getPublicKey() was called");
        logger.info("getPublicKey() ended successfully");
        return new ResponseEntity<com.rsa.KEY>(this.publicKey, HttpStatus.OK);
    }

    @PostMapping(value = "/signature", produces={"application/json"})
    public ResponseEntity<?> createSignature(@RequestBody String fileAsString) throws URISyntaxException {
        logger.info("createSignature() was called");
        //int hashed = fileAsString.hashCode(); //hashed msg
        String enc = rsa.encrypt(fileAsString, privateKey);
        logger.info("createSignature() ended successfully");
        return new ResponseEntity<>(enc, HttpStatus.OK);
    }
}
