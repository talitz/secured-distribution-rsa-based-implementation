package com.rsa;

import java.math.BigInteger;

public class RSA {
    /* Encrypt the given plain-text message. */
    public static String decrypt(String message, KEY key) {
        //String to BigInteger
        BigInteger ciphertext = new BigInteger(message);
        return new String(ciphertext.modPow(key.getE(), key.getN()).toByteArray());
    }
}