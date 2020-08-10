//package com.tests;
//
//import com.rsa.KEY;
//import com.rsa.RSA;
//
//public class MAIN {
//
//	public MAIN() {
//		// TODO Auto-generated constructor stub
//	}
//	//this function will be in edge class to verify the authenticity of the file
//	public static boolean ValidSig(String sig, String message, KEY PublicKey) {
//		//step 1:take original message and hash it
//		int hashed = message.hashCode();
//		System.out.println("@validSig hashed message = " + hashed);
//		//step 2:decrypt file sig
//		//String DecryptedSig = r.decrypt(sig, PublicKey);
//		String DecryptedSig = RSA.decrypt(sig, PublicKey);
//
//		System.out.println("@validSig DecryptedSig = " + DecryptedSig);
//		//step 3:compare
//	    return Integer.toString(hashed).equalsIgnoreCase(DecryptedSig);
//	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
////		distributer dis = new distributer();
////
////		String message1 = "hello world, i'm the king of all fuck u";
////		String sig = dis.getSignature(message1);
////		String message2 = "hello world, i'm the king of all fuck you";
////
////		//String plaintext = r.decrypt(sig, dis.getPublicKey());
////
////		boolean ValidMessage1 = ValidSig(sig,message1,dis.getPublicKey());
////		boolean ValidMessage2 = ValidSig(sig,message2,dis.getPublicKey());
//
////		System.out.println("@validSig comparison original message= " + ValidMessage1);
////		System.out.println("@validSig comparison corrupted message = " + ValidMessage2);
//	}
//}
