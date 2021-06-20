package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HasherSHA256 {

	public static String hashSHA256Hex(String t) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(t.getBytes(StandardCharsets.UTF_8));
			
			final StringBuilder hexString = new StringBuilder();
	        for (int i = 0; i < encodedhash.length; i++) {
	            final String hex = Integer.toHexString(0xff & encodedhash[i]);
	            if(hex.length() == 1) 
	              hexString.append('0');
	            hexString.append(hex);
	        }
        
        	return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
