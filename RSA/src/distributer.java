import java.math.BigInteger;

public class distributer {
	
	public static KEY PublicKey;
	private static KEY PrivateKey;
	private static RSA rsa;
	public distributer() {
		rsa = new RSA();
		rsa.generateKeys();
		PublicKey = rsa.getPublicKey();
		PrivateKey = rsa.getPrivateKey();
	}
	public String getSignature(String message) {
		int hashed = message.hashCode(); //hashed msg
		return rsa.encrypt(Integer.toString(hashed), PrivateKey);	
	}
	public KEY getPublicKey() {
		return PublicKey;
	}

}
