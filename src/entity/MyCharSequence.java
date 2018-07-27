package entity;

import java.util.List;

public interface MyCharSequence {
	
	String getCharSequence();
	
	boolean addElement(MyCharSequence element) ;
	
	boolean removeElement(MyCharSequence element) ;

	MyCharSequence getChild(int i) ;
	
	List<MyCharSequence> getList ();
	
	void setList(List<MyCharSequence> list);

	MyCharSequence getFirstElement();
}
