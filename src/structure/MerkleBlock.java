package structure;

import java.util.List;

public class MerkleBlock<T extends Hasheable> implements Hasheable {

	private String prevHash;
	private MerkleTree<T> merkleTree;

	public MerkleBlock() {}
	
	public MerkleBlock(String prevHash, List<T> data) {
		this.prevHash = prevHash;
		this.merkleTree = new MerkleTree<T>(data);
	}

	@Override
	public String getHash() {
		return merkleTree.getHash();
	}

	public String getPrevHash() {
		return prevHash;
	}

	@Override
	public String recalculateHash() {
		return merkleTree.recalculateHash();
	}
	
	public MerkleTree<T> getMerkleTree() {
		return merkleTree;
	}
}
