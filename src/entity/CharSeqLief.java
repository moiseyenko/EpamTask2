package entity;

public class CharSeqLief implements CharSeq {
	
	private String value;
	
	public CharSeqLief(String value) {
		this.value = value;
	}

	
	@Override
	public String getCharSequence() {
		return value;
	}	

}
