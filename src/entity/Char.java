package entity;

import java.util.List;

public class Char implements MyCharSequence {
	
	private char value;

	public Char(char value) {
		this.value = value;
	}
	
	public Char(String value) {
		this.value = value.charAt(0);
	}

	char getChar() {
		return value;
	}

	void setChar(char value) {
		this.value = value;
	}
	
	@Override
	public String getCharSequence() {
		return String.valueOf(value);
	}

	@Override
	public boolean addElement(MyCharSequence element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeElement(MyCharSequence element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MyCharSequence getChild(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<MyCharSequence> getList() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setList(List<MyCharSequence> list) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public MyCharSequence getFirstElement() {
		throw new UnsupportedOperationException();
	}
	
	

}
