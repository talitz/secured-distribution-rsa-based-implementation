package com.receiverservice;

import java.io.Serializable;

public class Message implements Serializable {
    private String fileAsString;
    private String signature;

    public Message() { }

    public Message(String fileAsString, String signature) {
        this.fileAsString = fileAsString;
        this.signature = signature;
    }

    public String getFileAsString() {
        return fileAsString;
    }

    public void setFileAsString(String fileAsString) {
        this.fileAsString = fileAsString;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
