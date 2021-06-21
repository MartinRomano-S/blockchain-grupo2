package blockchain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import structure.Hasher;
import structure.MerkleBlock;
import structure.MerkleTree;
import transactions.HasheableTransaction;

public class TransactionMerkleBlockChainTest {

	private List<MerkleBlock<?>> merkleBlockChain;
	
	public void initEach() {
		List<HasheableTransaction> transactions = new ArrayList<>();
		merkleBlockChain = new ArrayList<>();
		transactions.add(new HasheableTransaction("Messi", "Maradona", 300D));
		transactions.add(new HasheableTransaction("Maradona", "Messi", 300D));
		transactions.add(new HasheableTransaction("Messi", "Pelé", 300D));
		transactions.add(new HasheableTransaction("Pelé", "Ronaldinho", 300D));
		merkleBlockChain.add(new MerkleBlock<HasheableTransaction>("0", transactions));
		transactions = new ArrayList<>();
		transactions.add(new HasheableTransaction("Maradona", "Messi", 300D));
		transactions.add(new HasheableTransaction("Pelé", "Ronaldinho", 300D));
		transactions.add(new HasheableTransaction("Messi", "Maradona", 300D));
		transactions.add(new HasheableTransaction("Pelé", "Ronaldinho", 300D));
		merkleBlockChain.add(new MerkleBlock<HasheableTransaction>(merkleBlockChain.get(merkleBlockChain.size() - 1).getHash(), transactions));
		transactions = new ArrayList<>();
		transactions.add(new HasheableTransaction("Agüero", "Pelé", 300D));
		transactions.add(new HasheableTransaction("Maradona", "Ronaldinho", 300D));
		transactions.add(new HasheableTransaction("Messi", "Maradona", 300D));
		transactions.add(new HasheableTransaction("Pelé", "Agüero", 300D));
		merkleBlockChain.add(new MerkleBlock<HasheableTransaction>(merkleBlockChain.get(merkleBlockChain.size() - 1).getHash(), transactions));
	}
	
	@Test
	public void testValidTransactionBlockChain() {
		initEach();
		assertEquals(true, Hasher.isValidMerkleChain(merkleBlockChain));
	}
	
	@Test
	public void testInvalidTransactionBlockChain() {
		
		initEach();
		@SuppressWarnings("unchecked")
		MerkleBlock<HasheableTransaction> b3 = (MerkleBlock<HasheableTransaction>) merkleBlockChain.get(2);
		MerkleTree<HasheableTransaction> b4 = b3.getMerkleTree();
		List<HasheableTransaction> b5 = b4.getDataList();
		b5.get(2).setReceiver("ROMPI TODO");
		
		assertEquals(false, Hasher.isValidMerkleChain(merkleBlockChain));
	}
}
