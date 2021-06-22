package structure;

import java.util.Date;

public class Block<T> {

	private String hash;
	private String prevHash;
	private long timeStamp;
	
	//Data
	private T data;

	public Block() {}
	
	public Block(String prevHash, T data) {
		this.prevHash = prevHash;
		this.data = data;
		Date today = new Date();
		this.timeStamp = today.getTime();
		this.hash = recalculateHash();
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

	public T getData() {
		return data;
	}

	public void setData(final T data) {
		this.data = data;
		this.hash = Hasher.hashSHA256Hex(this.prevHash + data.toString() + this.timeStamp);
	}
	
	public String recalculateHash() {
		return Hasher.hashSHA256Hex(this.prevHash + data.toString() + timeStamp);
	}
}
