package entity;

public class CharSeqLief implements CharSeq {

	private String value;

	public CharSeqLief(String value) {
		this.value = value;
	}

	// Covert CharSeqLief into String
	@Override
	public String getCharSequence() {
		return value;
	}

}
