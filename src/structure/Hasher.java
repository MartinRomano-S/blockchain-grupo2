package structure;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Hasher {

	/**
	 * M�todo para hacer un hash en SHA-256 de una cadena
	 * @param t: cadena a hashear
	 * @return cadena hash en SHA-256
	 */
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
	
	/**
	 * M�todo para validar la cadena de bloques.
	 * @param blockChain: Cadena a validar
	 * @return true en caso de v�lida y falso en caso de err�nea
	 */
	public static boolean isValidChain(List<Block<?>> blockChain) {
		
		Block<?> current;
		Block<?> previous;
		
		for(int i=1; i < blockChain.size(); i++) {
			
			current = blockChain.get(i);
			previous = blockChain.get(i - 1);
			
			String currHash = current.getHash();
			String previousHash = previous.getHash();
			
			//Primero nos fijamos que el hash del bloque actual sea v�lido volviendo a hashearlo
			if(!currHash.equals(current.recalculateHash()))
				return false;
			
			//Luego nos fijamos que el hash del bloque anterior sea v�lido compar�ndolo con
			//el hash anterior que tiene el bloque actual
			if(!previousHash.equals(current.getPrevHash()))
				return false;
		}
		
		return true;
	}
	
	/**
	 * M�todo para validar la cadena de bloques Merkle.
	 * La diferenciamos de la cadena normal para no generar confusi�n
	 * con m�s interfaces y clases gen�ricas
	 * @param blockChain: Cadena Merkle a validar
	 * @return true en caso de v�lida y falso en caso de err�nea
	 */
	public static boolean isValidMerkleChain(List<MerkleBlock<?>> blockChain) {
		
		MerkleBlock<?> current;
		MerkleBlock<?> previous;
		
		for(int i=1; i < blockChain.size(); i++) {
			
			current = blockChain.get(i);
			previous = blockChain.get(i - 1);
			
			String currHash = current.getHash();
			String previousHash = previous.getHash();
			
			//Primero nos fijamos que el hash del bloque actual sea v�lido volviendo a hashearlo
			if(!currHash.equals(current.recalculateHash()))
				return false;
			
			//Luego nos fijamos que el hash del bloque anterior sea v�lido compar�ndolo con
			//el hash anterior que tiene el bloque actual
			if(!previousHash.equals(current.getPrevHash()))
				return false;
		}
		
		return true;
	}
}
