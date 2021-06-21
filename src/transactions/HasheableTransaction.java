package transactions;

import java.util.Date;

import structure.Hasheable;
import structure.Hasher;

public class HasheableTransaction implements Hasheable {
	private String transmitter;
	private String receiver;
	private Double mount;
	private long timeStamp;
	private String hash;
	
	public HasheableTransaction() {}
	
	public HasheableTransaction(String transmitter, String receiver, Double mount) {
		this.transmitter = transmitter;
		this.receiver = receiver;
		this.mount = mount;
		Date today = new Date();
		this.timeStamp = today.getTime();
		this.hash = recalculateHash();
	}

	public String getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(String transmitter) {
		this.transmitter = transmitter;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Double getMount() {
		return mount;
	}

	public void setMount(Double mount) {
		this.mount = mount;
	}

	@Override
	public String getHash() {
		return hash;
	}

	@Override
	public String recalculateHash() {
		return Hasher.hashSHA256Hex(transmitter + receiver + mount + timeStamp);
	}
}
