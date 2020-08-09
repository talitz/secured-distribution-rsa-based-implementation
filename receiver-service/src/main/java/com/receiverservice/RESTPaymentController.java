package com.receiverservice;

import com.google.gson.Gson;
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
@RequestMapping("/receiver-service")
public class RESTPaymentController {

    @Autowired
    public LoggingController logger;

    @Autowired
    private Gson gson;

    private String privateKey = "", publicKey = "";

    @PostConstruct
    public void init() {
        privateKey = "this-is-the-private-key-bitch";
        publicKey = "this-is-the-public-key-bitch";
    }

    @GetMapping(value = "/public-key", produces={"application/json"})
    public ResponseEntity<String> getPublicKey() throws URISyntaxException {
        logger.info("getPublicKey() was called");
        logger.info("getPublicKey() ended successfully");
        return new ResponseEntity<>(this.publicKey, HttpStatus.OK);
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
