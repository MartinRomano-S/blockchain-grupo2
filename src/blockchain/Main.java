package blockchain;

import java.util.LinkedList;
import java.util.List;

public class Main {
	
	static List<Block> blockChain;

	public static void main(String[] args) {
		Transaction t = new Transaction();
		t.setTransmitter("Messi");
		t.setReceiver("Maradona");
		t.setMount(300D);
		
		//Habría que ver como manejar los enlaces
		//Supongo que con alguna utilidad de las collections tipo equalTo y eso
		//Quizás haciendo un wrapper que obtenga el hash del último elemento y antes
		//de guardar el nuevo, le setee el prevHash.
		//Ver caso de bloque génesis
		blockChain = new LinkedList<>();
		
		String hash = HasherSHA256.hashSHA256Hex(t.toString());
		System.out.println(hash);
		
		Block b = new Block();
	}
}

