package blockchain;

import java.util.ArrayList;
import java.util.List;

import structure.Block;
import structure.Hasher;
import transactions.Transaction;
import transactions.TransactionWithCurrency;

public class Main {
	
	static List<Block<?>> blockChain;

	public static void main(String[] args) {
		
		blockChain = new ArrayList<>();
		blockChain.add(new Block<Transaction>("0", new Transaction("Messi", "Maradona", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Maradona", "Messi", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Messi", "Pelé", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Pelé", "Ronaldinho", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Ronaldinho", "Ronaldo", 300D)));
		blockChain.add(new Block<Transaction>(blockChain.get(blockChain.size() - 1).getHash(), new Transaction("Ronaldo", "Agüero", 300D)));
		
		//Prueba de modificación de transacción 2.
		/*
		Block<Transaction> b = (Block<Transaction>) blockChain.get(2);
		b.setTransaction(new Transaction("Maradona", "Messi", 300D));
		*/
		if(Hasher.isValidChain(blockChain))
			System.out.println("Transacción validada");
		else
			System.out.println("Transacción errónea");
		
		blockChain.clear();
		blockChain.add(new Block<TransactionWithCurrency>("0", new TransactionWithCurrency("Messi", "Maradona", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Maradona", "Messi", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Messi", "Pelé", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Pelé", "Ronaldinho", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Ronaldinho", "Ronaldo", "USD", 300D)));
		blockChain.add(new Block<TransactionWithCurrency>(blockChain.get(blockChain.size() - 1).getHash(), new TransactionWithCurrency("Ronaldo", "Agüero", "USD", 300D)));
		
		if(Hasher.isValidChain(blockChain))
			System.out.println("Transacción validada");
		else
			System.out.println("Transacción errónea");
	}
}