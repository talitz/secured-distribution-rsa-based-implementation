public class Edge {
	
	public Edge() {
		// TODO Auto-generated constructor stub
	}
	//this function verify the authenticity of the file
	public static boolean ValidSig(String sig, String message, KEY PublicKey) {
		
		//step 1:take original message and hash it
		int hashed = message.hashCode();		
		//step 2:decrypt file sig 
		String DecryptedSig = RSA.decrypt(sig, PublicKey);			
		//step 3:compare
	    return Integer.toString(hashed).equalsIgnoreCase(DecryptedSig);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
