package com.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

	private BigInteger n, d, e, lcm;
	private int bitlen = 256;
	private KEY PrivateKey;
	private KEY PublicKey;

	//TODO - make constructor with data known already (p,q)
	public RSA() {
	}
	//TODO - make constructor with data known already (p,q,e)
	//public RSA() {
	//}
	
	public KEY getPrivateKey() {
		return PrivateKey;
	}
	public KEY getPublicKey() {
		return PublicKey;
	}

	public void generateKeys() {
		
		SecureRandom random = new SecureRandom();
		//generate big primes p & q
		BigInteger p = new BigInteger(bitlen, 100, random); //100% random prime
		BigInteger q = new BigInteger(bitlen, 100, random); //100% random prime

		//multiply p*q
		n = p.multiply(q);

		//lcm = (p-1 * q-1) / findGCD(p-1,q-1);
		BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); //m = (p-1)*(q-1)
		BigInteger gcd = (p.subtract(BigInteger.ONE)).gcd(q.subtract(BigInteger.ONE)); //gcd((p-1),(q-1))
		lcm = m.divide(gcd); //lcm = m/gcd((p-1),(q-1))

		e = new BigInteger(lcm.bitLength() - 1, 100, random); //1<e<lcm using the bits number to make upper limit

		d = e.modInverse(lcm); 
		
		PrivateKey = new KEY (e,n);
		PublicKey = new KEY (d,n);		
	}

    /* Encrypt the given plain-text message. */
    public String encrypt(String message, KEY key) {
        //String to BigInteger 
    		BigInteger plaintext = new BigInteger(message.getBytes());
        return plaintext.modPow(key.getE(), key.getN()).toString();
    }
    /* Encrypt the given plain-text message. */
    public static String decrypt(String message, KEY key) {
    		//String to BigInteger
        BigInteger ciphertext = new BigInteger(message);
        return new String(ciphertext.modPow(key.getE(), key.getN()).toByteArray());    	
    }
	
}
