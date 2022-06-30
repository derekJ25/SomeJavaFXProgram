package application;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainSystem {

	private String passwordSHA1 = "";

	public MainSystem() {
	}

	public String hashPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update(password.getBytes("utf8"));
		passwordSHA1 = String.format("%040x", new BigInteger(1, md.digest()));
		return passwordSHA1;
	}

}
