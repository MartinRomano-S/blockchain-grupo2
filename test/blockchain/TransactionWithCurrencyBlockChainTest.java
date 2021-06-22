package blockchain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import structure.Block;
import structure.Hasher;
import transactions.TransactionWithCurrency;

public class TransactionWithCurrencyBlockChainTest {

	private List<Block<?>> blockChain;
	
	public void initEach() {
		blockChain = new ArrayList<>();
		blockChain.add(new Block<TransactionWithCurrency>("0", new TransactionWithCurrency("Messi", "Maradona", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Maradona", "Messi", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Messi", "Pelé", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Pelé", "Ronaldinho", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Ronaldinho", "Ronaldo", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Ronaldo", "Agüero", "USD", 300D)));
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
		Block<TransactionWithCurrency> b2 = (Block<TransactionWithCurrency>) blockChain.get(2);
		b2.setData(new TransactionWithCurrency("Maradona", "Messi", "ARS", 300D));
		
		assertEquals(false, Hasher.isValidChain(blockChain));
	}
}
