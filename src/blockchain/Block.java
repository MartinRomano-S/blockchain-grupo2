package blockchain;

public class Block {

	private String hash;
	private String prevHash;
	
	//Creo que esto habria que reemplazarlo por el arbol interno
	private Transaction transaction;

	public Block() {}
	
	public Block(String prevHash, Transaction transaction) {
		super();
		this.hash = HasherSHA256.hashSHA256Hex(transaction.toString());
		this.prevHash = prevHash;
		this.transaction = transaction;
	}

	public String getHash() {
		return hash;
	}

	public String getPrevHash() {
		return prevHash;
	}

	public void setPrevHash(String prevHash) {
		this.prevHash = prevHash;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
		this.hash = HasherSHA256.hashSHA256Hex(transaction.toString());
	}
}
