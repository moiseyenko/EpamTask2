package entity;

import java.util.ArrayList;
import java.util.List;

public class Composite implements TextElement {

	private List<TextElement> list;

	public Composite() {
		list = new ArrayList<>();
	}

	// add element to list
	public boolean addElement(TextElement element) {
		return list.add(element);
	}

	public List<TextElement> getList() {
		return list;
	}

	public void setList(List<TextElement> list) {
		this.list = list;
	}

	// Covert CharSeqComponent into String
	@Override
	public String getCharSequence() {
		StringBuilder sb = new StringBuilder();
		for (TextElement element : list) {
			sb.append(element.getCharSequence());
		}
		return sb.toString();
	}

}
