package blockchain;

import java.util.ArrayList;
import java.util.List;

import structure.Hasher;
import structure.MerkleBlock;
import structure.MerkleTree;
import transactions.HasheableTransaction;

public class Main {
	
	static List<MerkleBlock<?>> merkleBlockChain;

	public static void main(String[] args) {
		
		//MERKLE
		List<HasheableTransaction> transactions = new ArrayList<>();
		merkleBlockChain = new ArrayList<>();
		transactions.add(new HasheableTransaction("Messi", "Maradona", 300D));
		transactions.add(new HasheableTransaction("Maradona", "Messi", 300D));
		transactions.add(new HasheableTransaction("Messi", "Pel�", 300D));
		transactions.add(new HasheableTransaction("Pel�", "Ronaldinho", 300D));
		merkleBlockChain.add(new MerkleBlock<HasheableTransaction>("0", transactions));
		//OJO usar new ArrayList ya que sino mantiene la referencia de la lista y se buguea todo
		transactions = new ArrayList<>();
		transactions.add(new HasheableTransaction("Maradona", "Messi", 300D));
		transactions.add(new HasheableTransaction("Pel�", "Ronaldinho", 300D));
		transactions.add(new HasheableTransaction("Messi", "Maradona", 300D));
		transactions.add(new HasheableTransaction("Pel�", "Ronaldinho", 300D));
		merkleBlockChain.add(new MerkleBlock<HasheableTransaction>(merkleBlockChain.get(merkleBlockChain.size() - 1).getHash(), transactions));
		transactions = new ArrayList<>();
		transactions.add(new HasheableTransaction("Ag�ero", "Pel�", 300D));
		transactions.add(new HasheableTransaction("Maradona", "Ronaldinho", 300D));
		transactions.add(new HasheableTransaction("Messi", "Maradona", 300D));
		transactions.add(new HasheableTransaction("Pel�", "Ag�ero", 300D));
		merkleBlockChain.add(new MerkleBlock<HasheableTransaction>(merkleBlockChain.get(merkleBlockChain.size() - 1).getHash(), transactions));

		if(Hasher.isValidMerkleChain(merkleBlockChain))
			System.out.println("Transacci�n validada");
		else
			System.out.println("Transacci�n err�nea");
		
		//Prueba de modificaci�n de transacci�n para que falle la validaci�n.
		@SuppressWarnings("unchecked")
		MerkleBlock<HasheableTransaction> b3 = (MerkleBlock<HasheableTransaction>) merkleBlockChain.get(2);
		MerkleTree<HasheableTransaction> b4 = b3.getMerkleTree();
		List<HasheableTransaction> b5 = b4.getDataList();
		b5.get(2).setReceiver("ROMPI TODO");
		
		if(Hasher.isValidMerkleChain(merkleBlockChain))
			System.out.println("Transacci�n validada");
		else
			System.out.println("Transacci�n err�nea");
	}
}