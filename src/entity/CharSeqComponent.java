package entity;
import java.util.ArrayList;
import java.util.List;

public class CharSeqComponent implements MyCharSequence{
	
	private List<MyCharSequence> list;
	
	public CharSeqComponent() {
		list = new ArrayList<>();
	}
	
	public boolean addElement(MyCharSequence element) {
		return list.add(element);
		
	}
	
	public boolean removeElement(MyCharSequence element) {
		return list.remove(element);
	}

	public MyCharSequence getChild(int i) {
		return list.get(i);
	}
	
	public List<MyCharSequence> getList (){
		return list;
	}
	
	@Override
	public String getCharSequence() {
		StringBuilder sb = new StringBuilder();
		for(MyCharSequence element:list) {
			sb.append(element.getCharSequence());
		}
		return sb.toString();
	}
	
	public void setList(List<MyCharSequence> list) {
		this.list = list;
		
	}

	public MyCharSequence getFirstElement() {
		return list.get(0);
	}

	
	
}
