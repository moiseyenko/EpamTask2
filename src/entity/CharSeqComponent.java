package entity;
import java.util.ArrayList;
import java.util.List;

public class CharSeqComponent implements CharSeq{
	
	private List<CharSeq> list;
	
	public CharSeqComponent() {
		list = new ArrayList<>();
	}
	
	public boolean addElement(CharSeq element) {
		return list.add(element);
		
	}
	
	public boolean removeElement(CharSeq element) {
		return list.remove(element);
	}

	public CharSeq getChild(int i) {
		return list.get(i);
	}
	
	public List<CharSeq> getList (){
		return list;
	}
	
	@Override
	public String getCharSequence() {
		StringBuilder sb = new StringBuilder();
		for(CharSeq element:list) {
			sb.append(element.getCharSequence());
		}
		return sb.toString();
	}
	
	public void setList(List<CharSeq> list) {
		this.list = list;
		
	}

	public CharSeq getFirstElement() {
		return list.get(0);
	}

	
	
}
