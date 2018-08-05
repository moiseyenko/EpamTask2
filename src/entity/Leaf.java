package entity;

public class Leaf implements TextElement {

	private String value;

	public Leaf(String value) {
		this.value = value;
	}

	// Covert CharSeqLief into String
	@Override
	public String getCharSequence() {
		return value;
	}

}
