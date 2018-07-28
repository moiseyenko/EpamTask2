package entity;

import java.util.ArrayList;
import java.util.List;

public class CharSeqComponent implements CharSeq {

	private List<CharSeq> list;

	public CharSeqComponent() {
		list = new ArrayList<>();
	}

	// add element to list
	public boolean addElement(CharSeq element) {
		return list.add(element);
	}

	public List<CharSeq> getList() {
		return list;
	}

	public void setList(List<CharSeq> list) {
		this.list = list;
	}

	// Covert CharSeqComponent into String
	@Override
	public String getCharSequence() {
		StringBuilder sb = new StringBuilder();
		for (CharSeq element : list) {
			sb.append(element.getCharSequence());
		}
		return sb.toString();
	}

}
