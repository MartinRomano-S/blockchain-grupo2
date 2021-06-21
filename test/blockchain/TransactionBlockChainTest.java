package blockchain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import structure.Block;
import structure.Hasher;
import transactions.Transaction;

public class TransactionBlockChainTest {

	private List<Block<?>> blockChain;
	
	public void initEach() {
		blockChain = new ArrayList<>();
		blockChain.add(new Block<Transaction>("0", new Transaction("Messi", "Maradona", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Maradona", "Messi", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Messi", "Pel�", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Pel�", "Ronaldinho", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Ronaldinho", "Ronaldo", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Ronaldo", "Ag�ero", 300D)));
	}
	
	@Test
	public void testValidTransactionBlockChain() {
		initEach();
		assertEquals(true, Hasher.isValidChain(blockChain));
	}
	
	@Test
	public void testInvalidTransactionBlockChain() {
		
		initEach();
		@SuppressWarnings("unchecked")
		Block<Transaction> b = (Block<Transaction>) blockChain.get(2);
		b.setTransaction(new Transaction("Maradona", "Messi", 300D));
		
		assertEquals(false, Hasher.isValidChain(blockChain));
	}
}
